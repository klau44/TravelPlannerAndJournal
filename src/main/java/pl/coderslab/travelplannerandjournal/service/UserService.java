package pl.coderslab.travelplannerandjournal.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.travelplannerandjournal.model.User;
import pl.coderslab.travelplannerandjournal.model.UserRequest;
import pl.coderslab.travelplannerandjournal.model.UserResponse;
import pl.coderslab.travelplannerandjournal.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserResponse findById(Long id) {
        return userRepository.findById(id)
                .map(UserResponse::toDto)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(UserResponse::toDto)
                .toList();
    }

    public UserResponse add(UserRequest userRequest) {
        User user = User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .build();
        User saved = userRepository.save(user);

        return UserResponse.toDto(saved);
    }

    public UserResponse update(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (userRequest.getName() != null) {
            user.setName(userRequest.getName());
        }

        if (userRequest.getEmail() != null) {
            user.setEmail(userRequest.getEmail());
        }

        User updated = userRepository.save(user);

        return UserResponse.toDto(updated);
    }

    public void delete(Long id) {
        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        userRepository.delete(userToDelete);
    }
}
