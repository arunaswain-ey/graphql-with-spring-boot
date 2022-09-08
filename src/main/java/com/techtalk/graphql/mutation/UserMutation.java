package com.techtalk.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.techtalk.graphql.dao.entity.User;
import com.techtalk.graphql.mutation.model.UserMutationInput;
import com.techtalk.graphql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserMutation implements GraphQLMutationResolver {

    @Autowired
    private UserService userService;

    public User createUser(final String firstName, final String lastName, final String dob, final String email
            , final String phone) {
        return userService.createUser(firstName, lastName, dob, email, phone);
    }

    public User updateUser(final UserMutationInput userMutationInput) {
        return userService.updateUser(userMutationInput);
    }
}
