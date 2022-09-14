package com.techtalk.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.techtalk.graphql.dao.entity.Post;
import com.techtalk.graphql.dao.entity.User;
import com.techtalk.graphql.dao.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserPostResolver implements GraphQLResolver<User> {

    @Autowired
    private PostRepository postRepository;

    @Transactional
    public List<Post> allPostsByUser(final User user) {
        return postRepository.findByUser(user);
    }
}
