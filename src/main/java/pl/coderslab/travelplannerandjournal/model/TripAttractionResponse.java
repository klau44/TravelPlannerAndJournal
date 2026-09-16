package pl.coderslab.travelplannerandjournal.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripAttractionResponse {

    private String attractionName;
    private String attractionAddress;
    private boolean visited;
}
