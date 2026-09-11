package pl.coderslab.travelplannerandjournal.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {

    @NotBlank(message = "Imię jest wymagane")
    @Size(max = 100, message = "Imię może mieć maksymalnie 100 znaków")
    private String name;

    @NotBlank(message = "Email jest wymagany")
    @Email(message = "Niepoprawny format adresu email")
    @Size(max = 150, message = "Email może mieć maksymalnie 150 znaków")
    private String email;

    @NotBlank(message = "Hasło jest wymagane")
    @Size(min = 8, max = 72, message = "Hasło musi mieć od 8 do 72 znaków")
    private String password;
}
