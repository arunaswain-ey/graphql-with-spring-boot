package com.techtalk.graphql.dao.repository;

import com.techtalk.graphql.dao.entity.Post;
import com.techtalk.graphql.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    public List<Post> findByUser(final User user);
}
