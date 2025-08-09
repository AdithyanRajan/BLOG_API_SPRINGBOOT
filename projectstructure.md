src
└── main
    └── java
        └── com
            └── example
                └── rebuildingblogapi
                    ├── config
                    │   ├── OpenApiConfig.java
                    │   └── SecurityConfig.java
                    │
                    ├── controller
                    │   ├── AuthController.java
                    │   ├── CommentController.java
                    │   ├── PostController.java
                    │   └── UserController.java
                    │
                    ├── dto
                    │   ├── CommentDto.java
                    │   ├── JwtAuthRequest.java
                    │   ├── JwtAuthResponse.java
                    │   ├── PostDto.java
                    │   └── UserDto.java
                    │
                    ├── entity
                    │   ├── Comment.java
                    │   ├── Post.java
                    │   └── User.java
                    │
                    ├── repository
                    │   ├── CommentRepository.java
                    │   ├── PostRepository.java
                    │   └── UserRepository.java
                    │
                    ├── security
                    │   ├── CustomUserDetails.java
                    │   ├── CustomUserDetailsService.java
                    │   ├── JwtAuthenticationFilter.java
                    │   └── JwtTokenHelper.java
                    │
                    ├── service
                    │   ├── CommentService.java
                    │   ├── PostService.java
                    │   ├── UserService.java
                    │   └── impl
                    │       ├── CommentServiceImpl.java
                    │       ├── PostServiceImpl.java
                    │       └── UserServiceImpl.java
                    │
                    └── RebuildingBlogapiApplication.java
