package pl.coderslab.travelplannerandjournal.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JournalEntryRequest {

    @NotNull
    private String content;
}
