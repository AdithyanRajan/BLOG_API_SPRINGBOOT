package com.example.rebuilding_blogapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Comments")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false,length = 100)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id",nullable=false)
    private Post post;
}
