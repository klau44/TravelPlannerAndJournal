package pl.coderslab.travelplannerandjournal.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @Builder.Default
    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<Trip> trips = new HashSet<>();
}