package pl.coderslab.travelplannerandjournal.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttractionDto {
    private String name;
    private String country;
    private String city;
    private String postcode;
    private String address;
    private List<String> categories;
    private String externalId;
}
