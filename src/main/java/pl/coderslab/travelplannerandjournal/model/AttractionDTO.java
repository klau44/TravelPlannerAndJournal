package pl.coderslab.travelplannerandjournal.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttractionDTO {
    private String name;
    private List<String> categories;
}
