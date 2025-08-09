package com.example.rebuilding_blogapi.service.impl;

import com.example.rebuilding_blogapi.dto.PostDto;
import com.example.rebuilding_blogapi.entity.User;
import com.example.rebuilding_blogapi.entity.Post;
import com.example.rebuilding_blogapi.dto.UserDto;
import com.example.rebuilding_blogapi.repository.UserRepository;
import com.example.rebuilding_blogapi.service.UserService;
import com.example.rebuilding_blogapi.dto.CommentDto;
import com.example.rebuilding_blogapi.entity.Comment;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userrepo;

    private UserDto usertodto(User user) {
        List<PostDto> postdtos = null;
        if (user.getPosts() != null) {
            postdtos = user.getPosts().stream().map(this::posttodto).collect(Collectors.toList());
        }
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(null)
                .posts(postdtos)
                .about(user.getAbout())
                .build();
                
    }
    private User dtotouser(UserDto userdto){
        return User.builder()
        .about(userdto.getAbout())
        .email(userdto.getEmail())
        .name(userdto.getName())
        .password(userdto.getPassword())
        .id(userdto.getId())
        .build();
    }

    private PostDto posttodto(Post post) {
        List<CommentDto> commentdto=null;
        if(post.getComments()!=null){
            commentdto=post.getComments().stream().map(this::commenttoDto).collect(Collectors.toList());
        }
        return PostDto.builder()
        .id(post.getId())
        .title(post.getTitle())
        .content(post.getContent())
        .imageName(post.getImageName())
        .user(usertodto(post.getUser()))
        .comment(commentdto)
        .build();
    }

    private CommentDto commenttoDto(Comment comment){
        return CommentDto.builder()
        .id(comment.getId())
        .content(comment.getContent()).build();
    }


    @Override
    public UserDto createUser(UserDto userDto) {
        User user=dtotouser(userDto);
        User saveduser=userrepo.save(user);
        return usertodto(saveduser);

    }

    @Override
    public void deleteUser(Integer userid) {
        userrepo.deleteById(userid);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=userrepo.findAll();
        return users.stream().map(this::usertodto).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Integer userid) {
        User user=userrepo.findById(userid).orElse(null);
        if(user==null){
            return null;
        }
        return usertodto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userid) {
        User user=userrepo.findById(userid).orElse(null);
        if(user==null){
            return null;
        }
        user.setAbout(userDto.getAbout());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        User saveduser=userrepo.save(user);
        return usertodto(saveduser);
    }

}
