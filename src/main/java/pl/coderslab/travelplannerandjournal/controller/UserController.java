package pl.coderslab.travelplannerandjournal.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.travelplannerandjournal.model.LoginRequest;
import pl.coderslab.travelplannerandjournal.model.RegisterRequest;
import pl.coderslab.travelplannerandjournal.model.UserResponse;
import pl.coderslab.travelplannerandjournal.service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return new ResponseEntity<>(userService.register(registerRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody LoginRequest loginRequest,
                                      HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        return new ResponseEntity<>(userService.login(loginRequest, httpRequest, httpResponse), HttpStatus.OK);
    }
}
