package com.techtalk.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.techtalk.graphql.dao.entity.Post;
import com.techtalk.graphql.dao.entity.User;
import com.techtalk.graphql.dao.repository.PostRepository;
import com.techtalk.graphql.dao.repository.UserRepository;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


@Service
public class PostResolver implements GraphQLQueryResolver, GraphQLMutationResolver, GraphQLSubscriptionResolver {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    private final ConcurrentHashMap<Long, FluxSink<Post>> subscribers = new ConcurrentHashMap<>();

    @Transactional(readOnly = true)
    public List<Post> getAllPosts(final int limit) {
        return postRepository.findAll().stream().limit(limit).collect(Collectors.toList());
    }

    @Transactional
    public Post createPost(final String mediaType, final String content, final long userID) {
        User existingUser = userRepository.findById(userID).orElseThrow(()
                -> new RuntimeException("Can not find User with ID :" + userID));
        Post newPost = new Post();
        newPost.setMediaType(mediaType);
        newPost.setContent(content);
        newPost.setPublishedTime(LocalDateTime.now());
        newPost.setUser(existingUser);
        postRepository.save(newPost);
        return newPost;
    }

    @Transactional
    public String addLikesToPost(final long postID) {
        Post post = postRepository.findById(postID).orElseThrow(()
                -> new RuntimeException("Can not find Post with ID :" + postID));
        long likes = post.getLikes() + 1;
        post.setLikes(likes);
        postRepository.save(post);

        //Emit updated data
        if (subscribers.get(postID) != null) {
            subscribers.get(postID).next(post);
        }
        return "Post like updated to "+likes;
    }

    public Publisher<Post> onPostUpdate(final long postID) {
        return Flux.create(subscriber
                        -> subscribers.put(postID, subscriber.onDispose(() -> subscribers.remove(postID, subscriber)))
                , FluxSink.OverflowStrategy.LATEST);
    }
}
