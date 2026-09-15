package pl.coderslab.travelplannerandjournal.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripAttractionResponse {

    private String tripName;
    private String tripDestination;
    private LocalDate tripStartDate;
    private LocalDate tripEndDate;
    private String attractionName;
    private String attractionAddress;
}
