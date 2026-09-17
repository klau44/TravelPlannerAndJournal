package pl.coderslab.travelplannerandjournal.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import pl.coderslab.travelplannerandjournal.authorization.Role;

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
    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @ToString.Exclude
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}