package com.techtalk.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.techtalk.graphql.dao.entity.Post;
import com.techtalk.graphql.dao.entity.User;
import com.techtalk.graphql.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserPostResolver implements GraphQLResolver<User> {

    @Autowired
    private PostService postService;

    public List<Post> allPostsByUser(final User user) {
        return postService.getAllPostsByUser(user);
    }
}
