package com.example.task_for_n1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;
    @ManyToOne(fetch = FetchType.LAZY)
    private Users user;
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
}