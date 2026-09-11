package pl.coderslab.travelplannerandjournal.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.travelplannerandjournal.exception.EmailAlreadyExistsException;
import pl.coderslab.travelplannerandjournal.model.Role;
import pl.coderslab.travelplannerandjournal.model.User;
import pl.coderslab.travelplannerandjournal.model.RegisterRequest;
import pl.coderslab.travelplannerandjournal.model.UserResponse;
import pl.coderslab.travelplannerandjournal.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse register(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new EmailAlreadyExistsException("Email już istnieje");
        }

        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

        User user = User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .passwordHash(encodedPassword)
                .role(Role.USER)
                .build();

        User saved = userRepository.save(user);

        return UserResponse.toDto(saved);
    }
}
