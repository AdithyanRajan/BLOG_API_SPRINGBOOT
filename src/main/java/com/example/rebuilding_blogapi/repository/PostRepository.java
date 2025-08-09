package com.example.rebuilding_blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.rebuilding_blogapi.entity.Post;
import com.example.rebuilding_blogapi.entity.User;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer>{
     List<Post> findByUser(User user);
}
