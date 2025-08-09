
5) 
✅ How to mentally picture @OneToMany(mappedBy = "user")
Think of it like saying:

“This entity is the inverse or passive side of a relationship — go check the other side (i.e., the Post entity’s user field) map my primary key to that column in the post table, basically for joining table”

# But remember `@OneToMany` field doesn't show in the database

 # you still need to do `post.setUser(user)` yourself before saving, or JPA won’t know who the parent is.

#  Swagger shows the Java-side model (your Post entity) which holds the entire User object

6) 
 # you still need to do `post.setUser(user)` yourself before saving, or JPA won’t know who the parent is.
 and for cascade and orphanremoval to work


7) because of cascade = CascadeType.ALL and `post.setUser(user)` when you do userRepository.save(user) the post is automatically saved in the post table database and not in the user table database

8) 
File	                        Purpose

AuthController.java	            Accepts login request, calls AuthenticationManager, returns JWT token

JwtTokenProvider.java	        Generates and validates JWT tokens

SecurityConfig.java	            Configures Spring Security to use JWT and permit public/private paths

JwtAuthenticationFilter.java	Intercepts requests, checks for JWT in header, validates it


| 🔢 Step | 📂 File/Component | 📄 File Name                    | 💡 What it does                                                     |
| ------- | ----------------- | ------------------------------- | ------------------------------------------------------------------- |
| 1️⃣     | `dto`             | `LoginRequest.java`             | Holds login credentials sent by user                                |
| 2️⃣     | `dto`             | `JwtAuthResponse.java`          | Holds the token we send back on successful login                    |
| 3️⃣     | `controller`      | `AuthController.java`           | Main REST controller for login                                      |
| 4️⃣     | `security`        | `JwtTokenHelper.java`           | Generates and validates JWT tokens                                  |
| 5️⃣     | `security`        | `JwtAuthenticationFilter.java`  | Intercepts requests, checks JWT, and sets authentication            |
| 6️⃣     | `service.impl`    | `CustomUserDetailsService.java` | Loads user by username (email or username), used by Spring Security |
| 7️⃣     | `config`          | `SecurityConfig.java`           | Main Spring Security configuration                                  |
| 8️⃣     | `config`          | `OpenApiConfig.java`            | Enables Swagger to work with JWT (for authorize button)             |
