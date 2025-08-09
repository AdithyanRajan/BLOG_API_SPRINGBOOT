package com.example.rebuilding_blogapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rebuilding_blogapi.dto.CommentDto;
import com.example.rebuilding_blogapi.repository.CommentRepository;
import com.example.rebuilding_blogapi.repository.PostRepository;
import com.example.rebuilding_blogapi.service.CommentService;
import com.example.rebuilding_blogapi.entity.Comment;
import com.example.rebuilding_blogapi.entity.Post;
@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private PostRepository postrepo;
    @Autowired
    private CommentRepository commentrepo;
    @Override
    public CommentDto createComment(CommentDto commentdto, Integer postid) {
        Post post=postrepo.findById(postid).orElse(null);
        if(post==null)return null;
        Comment comment=Comment.builder()
                     
                        .content(commentdto.getContent())
                        .post(post)
                        .build();
        comment=commentrepo.save(comment);
        return CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .build();
    }

    @Override
    public void deleteComment(Integer commentid) {
      commentrepo.deleteById(commentid);
        
    }
    
}
