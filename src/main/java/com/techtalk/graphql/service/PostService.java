package com.techtalk.graphql.service;

import com.techtalk.graphql.dao.entity.Post;
import com.techtalk.graphql.dao.entity.User;
import com.techtalk.graphql.dao.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public Post createPost(final String mediaType, final String content, final int userID) {
        User existingUser = userService.getUser(userID).orElseThrow(()
                -> new RuntimeException("Can not find User with ID :" + userID));
        Post newPost = new Post();
        newPost.setMediaType(mediaType);
        newPost.setContent(content);
        newPost.setPublishedTime(LocalDateTime.now());
        newPost.setUser(existingUser);
        postRepository.save(newPost);
        return newPost;
    }

    @Transactional(readOnly = true)
    public List<Post> getAllPosts(final int limit) {
        return postRepository.findAll().stream().limit(limit).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Post> getAllPostsByUser(User user) {
        return postRepository.findByUser(user);
    }
}
