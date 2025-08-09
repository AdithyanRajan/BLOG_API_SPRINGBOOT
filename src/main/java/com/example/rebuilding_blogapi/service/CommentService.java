package com.example.rebuilding_blogapi.service;

import com.example.rebuilding_blogapi.dto.CommentDto;

public interface CommentService {

    void deleteComment(Integer commentid);
    CommentDto createComment(CommentDto commentdto,Integer postid);
}