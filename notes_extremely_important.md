jwt,rbac,encrypted password,exception handling, payloads & utils,config

1) 
```java

User.java
@Builder.Default
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Post> posts=new ArrayList<>();

Post.java
 @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_Id",nullable=false)
    private User user;

```
 1) @OneToMany "hey so one user can have many posts, so when user.setpost(postobj) store it in the post table not in my user table, also the post table would have my primary key in the "user" column(@joincolumn would do this job) which will be renamed as 'user_id' "

 2) after post.setUser(user); but in the backend only the primary key of the user will be set in the record but we still send the whole user instance post.setUser(userobj) because in future we will use post.getUser()

 3) also here 
 ```java
 @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Post> posts=new ArrayList<>();
```
Because of @onetomany by default when we fetch the user the posts are not fetched
- - - - - - - - - -- - -- -  - - -
 so Post table is the owner because it has the foreign key in it's table

 but User table is the Parent and Post table is the Child

2) 
@ManyToOne(fetch = FetchType.LAZY)
private User user;

means 
"when you fetch the post entity don't fetch the user field only fetch it when i say post.getuser()"

if you don't write fetch = FetchType.LAZY
then when you write -> Post post = postRepository.findById(1).get();

SELECT * FROM posts WHERE id = 1;  -- ✅ This gets post row

-- And if FetchType = EAGER
SELECT * FROM users WHERE id = 5;  -- 😮 This runs immediately

so you get the entire user object (jpa transforms table rows to java objects)

Note: You can use (fetch = FetchType.LAZY) only on relationships between entities, not on simple fields like String, int, boolean, etc.

3) 
so `user` field is a **lazy proxy** (i.e., not loaded yet).

Now when you return the Post entity from controller
Before returning, **Spring uses Jackson to serialize `post` into JSON** for Swagger/HTTP.

 **Jackson accesses `post.getUser()` to serialize** because It simply walks through **every field**, calls the corresponding getter (`getUser()`), and prints the returned value.

 4) 
 so even after using (fetch = FetchType.LAZY) anyways jackson gets the user so usually we use Dto's
 ❓ If we always use DTOs, then why use @ManyToOne(fetch = FetchType.LAZY)?
Because even though you're not serializing the entity directly, the entity is still used internally in the service/repo layer, and LAZY fetching protects performance in those layers.

Note:
cascade = CascadeType.ALL means “When you do something to the parent, automatically do it to the child too.”
if you add the user then add it's tasks too and if you remove the user remove it's tasks too

orphanRemoval = true means if the user is present but the post is deleted so delete that post from the post table too

Without @Builder.Default, the posts list would be null instead of an empty list. can cause issues

5) 
User user = User.builder()
    .name("Rohan")
    .email("rohan@example.com")
    .build(); // ❌ At this point, user.id == null

Post post = Post.builder()
    .title("Hello")
    .build();

post.setUser(user); // ❌ Still, user.id is null at this point (only primary key of user is stored)

user.setPosts(List.of(post)); // ✅ Setting both sides of relationship

userRepository.save(user); // ✅ Now both User and Post get saved

🤯 Here’s the Trick — JPA Magic (Persistence Context):

1) userRepository.save(user) → JPA detects cascade and prepares to insert both.

2) JPA first inserts the User, gets the auto-generated ID.

3) Then it sets that ID into the user_id of the Post object.

4) Then inserts the Post with correct user_id.

5) All of this happens in one transaction.

- - - - - - --  - - - - - - -- -- - - - - - - --  - - - - - - -- --- - - - - - --  - - - - - - -- --- - - - - - --  - - - - - - -- --- - - - - - --  - - - - - - -- --- - - - - - --  - - - - - - -- --- - - - - - --  - - - - - - -- -- - - - - - - --  - - - - - - -- --- - - - - - --  - - - - - - -- --- - - - - - --  - - - - - -

Repository

1)
✅ Do we need @Repository on interface-based repositories?
No, not required if you're extending JpaRepository — because:

Spring Data JPA automatically detects any interface that extends JpaRepository and registers it as a Spring Bean, behind the scenes.

2)
✅ You always need to say JpaRepository<Entity, IDType>?

3) 
Spring Data JPA provides automatic methods for primary key-based operations.
But if I want to filter rows in the table using non-primary key fields, I need to define custom methods in the repository.

For example, if I already have a Post object, I can easily access its fields like:
post.getId(), post.getContent(), post.getUser(), etc.

But if I want to query multiple rows from the Post table using some field other than the ID (like user, title, etc.),
I need to create custom methods like findByUser() or findByTitle().

very important 
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Dto's

1) 
DTOs are just plain Java classes (POJOs) used to send or receive data in the API layer.

They are not connected to the database directly.

You control exactly which fields to include, exclude, or make optional in the DTO.

Even if the entity requires a field (e.g., @NotNull, @Column(nullable = false)), you can choose not to include it in the DTO — or include it as optional.

💡 Think of it this way:
Entity = talks to the database.

DTO = talks to the client/browser/postman.

2) 
Exactly! ✅

The @Data annotation from Lombok is a shortcut that generates all the boilerplate code like:

@Getter
@Setter
@ToString
@EqualsAndHashCode
and a default @RequiredArgsConstructor
- - - - - - - -- -  - - - - - - - - - -- - - - - - - - - -  - - - - - - - - - - - - - - - -- - - - - - - - - 
Service


- - - - - -- - - - - - - - - -  - - - - - - - - - - - - - - - -- - - - - - - - - - - - - - - - - -  -
Note:
you can do something like
User user=userrepo.findbyid(id)
post.setuser(user)

postrepo.save(post)

but you cannot do 
user.setpost(post)
userrepo.save(user)
you gotta add post.set(user) before saving user 


2) 

 @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Post> posts=new ArrayList<>();
    
But this does not fetch the user's posts yet, because posts is set to lazy fetch by default (@OneToMany(mappedBy = "user") is lazy by default).

3) 
when i say 
@autowire
private PostService postservice;

If it's a class like a service class and when we do @Service, Spring directly creates an object, i.e., singleton bean. And if it's an interface, then it creates a class implementing that interface and then creates an object of it

3.5) 
To use methods like .orElse or .orElseThrow the return type should be Option<class_name>

4) 
OpenApiConfig.java
(Optional) Configures Swagger/OpenAPI for authorization header input (helpful for testing secured endpoints).

SecurityConfig.java
Core of Spring Security config.
Enables JWT filter, disables session management, configures which endpoints require authentication.

AuthController.java
Exposes /login or /authenticate endpoint to accept username & password and return JWT.

CustomUserDetails.java
Wraps your User entity to implement UserDetails for Spring Security.

CustomUserDetailsService.java
Loads user from DB (via UserRepository) and returns a CustomUserDetails object.

JwtAuthenticationFilter.java
Intercepts requests, extracts JWT from header, validates it, sets authentication in the security context.

JwtTokenHelper.java
Generates, parses, and validates JWT tokens.

JwtAuthRequest.java
DTO: used to receive login credentials (username & password).

JwtAuthResponse.java
DTO: used to send back the JWT token after successful login.

Order:
1. CustomUserDetails ✅
2. CustomUserDetailsService ✅
3. JwtTokenHelper ✅
4. JwtAuthRequest + JwtAuthResponse (DTOs)
5. AuthController
6. SecurityConfig
7. JwtAuthenticationFilter
8. OpenApiConfig (optional)