package com.example.rebuilding_blogapi.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name="Posts")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false)
    private String title;

    @Column(nullable=false,columnDefinition = "Text")
    private String content;

    private Date createdAt;

    private String imageName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_Id",nullable=false)
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment> comments=new ArrayList<>();
}
