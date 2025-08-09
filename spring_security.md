## go through authcontroller.java and securityconfig.java

## 🧠 Refined Flow — Spring Security + JWT Authentication

### 1️⃣ **AuthenticationManager Bean**

```java
@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
}
```

- `AuthenticationConfiguration` is a Spring Security helper.
- It **collects all `AuthenticationProvider` beans** you’ve defined.
- It then **wraps them in a `ProviderManager`** (which _is_ the `AuthenticationManager`).
- So yes — when you call `authenticationManager.authenticate(...)`, you’re talking to a `ProviderManager`.

---

### 2️⃣ **AuthenticationManager.authenticate(...)**

```java
authenticationManager.authenticate(
    new UsernamePasswordAuthenticationToken(
        request.getEmail(),
        request.getPassword()
    )
);
```

- The `ProviderManager` **iterates** over all registered `AuthenticationProvider`s.
- For each provider, it calls:

```java
provider.supports(authentication.getClass())
```

to see if it can handle this type of `Authentication` object.(new usernamepasswordauthenticationtoken)

---

### 3️⃣ **DaoAuthenticationProvider**

```java
@Bean
public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(customUserDetailsService);
    provider.setPasswordEncoder(passwordEncoder);
    return provider;
}
```

-DaoAuthenticationProvider implements AuthenticationProvider which has methods like support(to see if the class UsernamePasswordAuthenticationToken is present or not) and then Authenticate authenticate(to authenticate and return an authenticate object)

- `DaoAuthenticationProvider.supports(...)` returns `true` for `UsernamePasswordAuthenticationToken`.
- So it:

0. So Inside DaoAuthenticationProvider.authenticate(Authentication authentication):
   The method receives that token object.( new UsernamePasswordAuthenticationToken(
   request.getEmail(),
   request.getPassword()
   ))
   using simple getters

1. Calls your `UserDetailsService.loadUserByUsername(email)`
1. Retrieves the `UserDetails` object
1. Compares the stored (hashed) password with the provided one using your `PasswordEncoder`
1. If OK → returns a **fully authenticated** `UsernamePasswordAuthenticationToken` with roles/authorities
1. If not OK → throws an exception (`BadCredentialsException`, etc.)

---

### 4️⃣ **JWT Integration**

- Once authentication succeeds, your controller/service generates a JWT.
- Next time a request comes in with the JWT, you **bypass** this login flow:

  - Instead, a `JwtAuthenticationFilter` extracts and validates the token
  - It then sets an already-authenticated user in `SecurityContextHolder`

Note.
Both AuthenticationProvider and ProviderManager have an authenticate method
Both are different