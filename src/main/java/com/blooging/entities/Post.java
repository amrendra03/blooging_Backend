package com.blooging.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name = "post_title",length = 100)
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;
}
