package com.example.rebuilding_blogapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rebuilding_blogapi.dto.CommentDto;
import com.example.rebuilding_blogapi.dto.PostDto;
import com.example.rebuilding_blogapi.dto.UserDto;
import com.example.rebuilding_blogapi.entity.Comment;
import com.example.rebuilding_blogapi.entity.Post;
import com.example.rebuilding_blogapi.entity.User;
import com.example.rebuilding_blogapi.repository.PostRepository;
import com.example.rebuilding_blogapi.repository.UserRepository;
import com.example.rebuilding_blogapi.service.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postrepo;

    @Autowired
    private UserRepository userrepo;

    private Post dtotopost(PostDto postdto){
        return Post.builder()
        .id(postdto.getId())
        .title(postdto.getTitle())
        .content(postdto.getContent())
        .build();
    }
    private PostDto posttodto(Post post){
        List<CommentDto> comments=null;
        if(post.getComments()!=null){
            comments=post.getComments().stream().map(this::commenttodto).collect(Collectors.toList());
        }
        return PostDto.builder()
        .id(post.getId())
        .title(post.getTitle())
        .content(post.getContent())
        .comment(comments)
        .user(usertodto(post.getUser()))
        .build();
    }
    private UserDto usertodto(User user){
        return UserDto.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .password(null)
        .about(user.getAbout())
        .build();
    }
    private CommentDto commenttodto(Comment comment){
        return CommentDto.builder()
        .id(comment.getId())
        .content(comment.getContent())
        .build();
    }
    @Override
    public PostDto createPost(PostDto postdto, Integer userid) {
        User user=userrepo.findById(userid).orElse(null);
        if(user==null){
            return null;
        }
       Post post=dtotopost(postdto);
        post.setUser(user);
        post=postrepo.save(post);
        PostDto savedpostdto=posttodto(post);  
        return savedpostdto;
        
    }

    @Override
    public void deletepost(Integer postid) {
        postrepo.deleteById(postid);
        
    }

    @Override
    public List<PostDto> getallposts() {
        List<Post> allposts=postrepo.findAll();
        return allposts.stream().map(this::posttodto).collect(Collectors.toList());
    }

    @Override
    public PostDto getpostbyid(Integer postid) {
        Post post=postrepo.findById(postid).orElse(null);
        if(post==null){return null;}
        return posttodto(post);
    }

    @Override
    public List<PostDto> getpostsbyuser(Integer userid) {
        User user=userrepo.findById(userid).orElse(null);
        if(user==null){
            return null;
        }
        List<Post> allposts=postrepo.findByUser(user);
       return allposts.stream().map(this::posttodto).collect(Collectors.toList());
    }

    @Override
    public PostDto updatePost(PostDto postdto, Integer postid) {
        Post post=postrepo.findById(postid).orElse(null);
        if(post==null){
            return null;
        }
        post.setTitle(postdto.getTitle());
        post.setContent(postdto.getContent());
        post=postrepo.save(post);
        return posttodto(post);
    }

    
}
