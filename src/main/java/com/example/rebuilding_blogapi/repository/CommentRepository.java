package com.example.rebuilding_blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rebuilding_blogapi.entity.Comment;
import com.example.rebuilding_blogapi.entity.Post;
import java.util.List;


public interface CommentRepository extends JpaRepository<Comment,Integer>{
    List<Comment> findByPost(Post post);
}
