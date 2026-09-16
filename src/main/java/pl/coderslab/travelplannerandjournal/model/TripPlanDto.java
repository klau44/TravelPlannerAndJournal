package pl.coderslab.travelplannerandjournal.model;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripPlanDto {

    private String tripName;
    private String tripDestination;
    private LocalDate tripStartDate;
    private LocalDate tripEndDate;
    private List<TripAttractionResponse> tripAttractions;
}
