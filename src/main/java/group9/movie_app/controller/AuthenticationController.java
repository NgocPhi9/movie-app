package group9.movie_app.controller;

import group9.movie_app.dto.AuthenticationRequest;
import group9.movie_app.dto.AuthenticationResponse;
import group9.movie_app.dto.ChangePasswordRequest;
import group9.movie_app.dto.RegisterRequest;
import group9.movie_app.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/movies-app")
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register (@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PutMapping("/user-admin/password")
    public ResponseEntity<String> changePassword (@RequestBody ChangePasswordRequest request) {
        service.changePassword(request);
        return ResponseEntity.ok("Password changed");
    }
}
