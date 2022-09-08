package com.techtalk.graphql.dao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Data
@EqualsAndHashCode
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    private LocalDate dob;

    private String email;

    private String phone;

    @OneToMany(mappedBy = "user")
    private Set<Post> posts;
}
