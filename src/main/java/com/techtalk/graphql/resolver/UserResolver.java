package com.techtalk.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.techtalk.graphql.dao.entity.User;
import com.techtalk.graphql.dao.repository.PostRepository;
import com.techtalk.graphql.dao.repository.UserRepository;
import com.techtalk.graphql.model.UserMutationInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<User> getUsers(final int count) {
        return userRepository.findAll().stream().limit(count).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<User> getUser(final long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public User createUser(final String firstName, final String lastName, final String dob, final String email
            , final String phone) {
        final User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDob(LocalDate.parse(dob));
        user.setEmail(email);
        user.setPhone(phone);
        user.setCreatedUser("demon");
        user.setCreatedDate(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(final UserMutationInput userMutationInput) {
        User existingUser = userRepository.findById(userMutationInput.getID()).orElseThrow(()
                -> new RuntimeException("Can not find User with ID :" + userMutationInput.getID()));
        existingUser.setEmail(userMutationInput.getEmail());
        userRepository.save(existingUser);
        return existingUser;
    }

    @Transactional(readOnly = true)
    public List<Object> getAllUserPost() {
        List list = userRepository.findAll();
        list.addAll(postRepository.findAll());
        return list;
    }
}
