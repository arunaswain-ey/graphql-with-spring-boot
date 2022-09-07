package com.techtalk.graphql.service;

import com.techtalk.graphql.dao.entity.User;
import com.techtalk.graphql.dao.repository.UserRepository;
import com.techtalk.graphql.mutation.model.UserMutationInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User createUser(final String firstName, final String lastName, final String dob, final String email
            , final String phone) {
        final User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDob(LocalDate.parse(dob));
        user.setEmail(email);
        user.setPhone(phone);
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> getUsers(final int count) {
        return userRepository.findAll().stream().limit(count).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<User> getUser(final int id) {
        return userRepository.findById(id);
    }

    @Transactional
    public User updateUser(final UserMutationInput userMutationInput) {
        Optional<User> user = userRepository.findById(userMutationInput.getID());
        User existingUser = user.orElseThrow(()
                -> new RuntimeException("Can not find User with ID :" + userMutationInput.getID()));
        existingUser.setEmail(userMutationInput.getEmail());
        existingUser.setPhone(userMutationInput.getPhone());
        userRepository.save(existingUser);
        return existingUser;
    }
}
