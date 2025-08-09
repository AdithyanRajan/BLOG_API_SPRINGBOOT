Thanks for sharing your project structure — this helps a lot!

---

### 🔑 Where to add password encryption:

**Most likely place:**

* `UserServiceImpl.java` (inside `service/impl/`) — the class where user registration or user creation logic happens.
* Or wherever you save a new `User` entity (could be inside the service or controller).

---

### Typical steps to encrypt password during user registration:

1. **Inject `PasswordEncoder` into your `UserServiceImpl` (or wherever user creation happens).**

2. **Before saving a new user, encode the plain password:**

```java
String rawPassword = userDto.getPassword();
String encodedPassword = passwordEncoder.encode(rawPassword);
user.setPassword(encodedPassword);
```

3. **Save the user with the encoded password** in the database.

---

### What if you don’t have password encoding in your service yet?

You’ll need to:

* Add a `@Bean` for `PasswordEncoder` in `SecurityConfig.java` if you haven’t already:

```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

* Inject `PasswordEncoder` into your service class:

```java
@Service
@RequiredArgsConstructor // if you use Lombok
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;  // inject here

    public User registerNewUser(UserDto userDto) {
        User user = new User();
        // set other fields

        // encode password here
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return userRepository.save(user);
    }
}
```

---

### So, **the file to modify** is:

* `UserServiceImpl.java`

---

### If you want, you can share `UserServiceImpl.java` or the registration method code, and I’ll help you add the encoding exactly.

---

Let me know if you want me to draft that code for you!
