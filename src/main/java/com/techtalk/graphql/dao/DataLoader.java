package com.techtalk.graphql.dao;

import com.techtalk.graphql.dao.entity.Post;
import com.techtalk.graphql.dao.entity.User;
import com.techtalk.graphql.dao.repository.PostRepository;
import com.techtalk.graphql.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DataLoader {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @PostConstruct
    public void loadInitialData() {
        User user = new User();
        user.setFirstName("Aruna");
        user.setLastName("Swain");
        user.setDob(LocalDate.parse("1987-09-01"));
        user.setEmail("Aruna.Swain@gmail.com");
        user.setPhone("+48 879678761");
        userRepository.save(user);

        Post post1 = new Post();
        post1.setMediaType("Audio");
        post1.setContent("test.mp3");
        post1.setPublishedTime(LocalDateTime.now());
        post1.setUser(user);
        Post post2 = new Post();
        post2.setMediaType("Video");
        post2.setContent("test.mp4");
        post2.setPublishedTime(LocalDateTime.now());
        post2.setUser(user);
        postRepository.save(post1);
        postRepository.save(post2);
    }
}
