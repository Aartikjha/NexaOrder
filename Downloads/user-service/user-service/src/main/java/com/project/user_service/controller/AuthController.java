package com.project.user_service.controller;

import com.project.user_service.model.User;
import com.project.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody User user) {
        String result = userService.register(user);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody User user) {
        String result = userService.login(user);
        return ResponseEntity.ok(result);
    }
}
















//| Code | Meaning |
//        |---|---|
//        | `@RestController` | This class handles API requests |
//        | `@RequestMapping("/auth")` | All APIs start with `/auth` |
//        | `@PostMapping("/register")` | POST `/auth/register` → register user |
//        | `@PostMapping("/login")` | POST `/auth/login` → login and get token |
//        | `@RequestBody` | Reads JSON from request body |
//        | `ResponseEntity.ok()` | Returns 200 OK response |

//        ## Your APIs Will Be
//```
//POST http://localhost:8081/auth/register
//POST http://localhost:8081/auth/login
