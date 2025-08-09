package com.example.rebuilding_blogapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.rebuilding_blogapi.dto.PostDto;
import com.example.rebuilding_blogapi.service.PostService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/api/post")
public class PostController {
    
    @Autowired
    private PostService postservice;

    @PostMapping("/{userid}")
    public PostDto postMethodName(@RequestBody PostDto postdto,@PathVariable Integer userid) {
       
        
        return postservice.createPost(postdto, userid);
    }
    @PutMapping("/{id}")
    public PostDto putMethodName(@PathVariable Integer id, @RequestBody PostDto postdto) {
       
        
        return postservice.updatePost(postdto, id);
    }
    @GetMapping("/")
    public List<PostDto> getMethodName() {
        return postservice.getallposts();
    }

    @GetMapping("/{postid}")
    public PostDto getMethodName(@PathVariable Integer postid) {
        return postservice.getpostbyid(postid);
    }
    @GetMapping("/user/{userid}")
    public List<PostDto> gedtMethodName(@PathVariable Integer userid) {
        return postservice.getpostsbyuser(userid);
    }
    @DeleteMapping("/{id}")
    public void deletepost(@PathVariable Integer id){
        postservice.deletepost(id);
    }
    
    
}
