package com.example.rebuilding_blogapi.service;
import com.example.rebuilding_blogapi.dto.PostDto;
import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postdto,Integer userid);
    PostDto updatePost(PostDto postdto,Integer postid);
    List<PostDto> getallposts();
    PostDto getpostbyid(Integer postid);
    List<PostDto> getpostsbyuser(Integer userid);
    void deletepost(Integer postid);
}
