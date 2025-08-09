package com.example.rebuilding_blogapi.dto;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String about;
    private List<PostDto> posts;        
}
