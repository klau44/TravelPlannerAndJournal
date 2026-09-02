package pl.coderslab.travelplannerandjournal.model;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private Set<Trip> trips;

    public static UserResponse toDto (User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .trips(user.getTrips())
                .build();
    }
}
