package com.techtalk.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.techtalk.graphql.dao.entity.Post;
import com.techtalk.graphql.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PostMutation implements GraphQLMutationResolver {

    @Autowired
    private PostService postService;

    public Post createPost(final String mediaType, final String content, final int userID) {
        return postService.createPost(mediaType, content, userID);
    }
}
