package jwt_demo.controller;

import org.springframework.web.bind.annotation.*;
import jwt_demo.model.User;
import jwt_demo.security.JwtUtil;

@RestController
public class AuthController {

    private JwtUtil jwtUtil = new JwtUtil();

    // LOGIN API
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        if (user.getUsername().equals("user123") &&
                user.getPassword().equals("password123")) {

            return jwtUtil.generateToken(user.getUsername());
        }

        return "Invalid Credentials";
    }

    // PROTECTED API
    @GetMapping("/protected")
    public String protectedRoute(@RequestHeader("Authorization") String token) {

        token = token.substring(7); // remove "Bearer "

        if (jwtUtil.validateToken(token)) {
            return "Access Granted!";
        }

        return "Invalid Token";
    }
}