package com.example.rebuilding_blogapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rebuilding_blogapi.dto.CommentDto;
import com.example.rebuilding_blogapi.service.CommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
    }

    @PostMapping("/{postid}")
    public CommentDto postMethodName(@RequestBody CommentDto commentdto, @PathVariable Integer postid) {

        return commentService.createComment(commentdto, postid);
    }

}
