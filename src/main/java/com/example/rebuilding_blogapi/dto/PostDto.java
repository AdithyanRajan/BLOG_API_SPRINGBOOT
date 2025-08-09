package com.example.rebuilding_blogapi.dto;

import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {

    private Integer id;
    private String title;
    private String content;
    private String imageName;
    private UserDto user;
    private List<CommentDto> comment;
}
