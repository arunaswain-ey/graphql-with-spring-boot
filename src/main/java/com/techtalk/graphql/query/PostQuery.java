package com.techtalk.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLResolver;
import com.techtalk.graphql.dao.entity.Post;
import com.techtalk.graphql.dao.entity.User;
import com.techtalk.graphql.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostQuery implements GraphQLQueryResolver {

    @Autowired
    private PostService postService;

    public List<Post> allPosts(final int limit) {
        return postService.getAllPosts(limit);
    }
}
