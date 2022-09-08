package com.techtalk.graphql.dao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@Entity
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "media_type", nullable = false)
    private String mediaType;

    private String content;

    @Column(name = "published_time", nullable = false)
    private LocalDateTime publishedTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
