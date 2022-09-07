package com.techtalk.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.techtalk.graphql.dao.entity.User;
import com.techtalk.graphql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserQuery implements GraphQLQueryResolver {

    @Autowired
    private UserService userService;

    public List<User> getUsers(final int count) {
        return this.userService.getUsers(count).stream().limit(count).collect(Collectors.toList());
    }

    public Optional<User> getUser(final int id) {
        return this.userService.getUser(id);
    }
}
