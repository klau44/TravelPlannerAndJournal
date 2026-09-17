package pl.coderslab.travelplannerandjournal.model.dto;

import lombok.*;
import pl.coderslab.travelplannerandjournal.model.entity.Trip;
import pl.coderslab.travelplannerandjournal.model.entity.TripAttraction;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripPlanResponse {

    private String tripName;
    private String tripDestination;
    private LocalDate tripStartDate;
    private LocalDate tripEndDate;
    private List<TripAttractionResponse> tripAttractions;

    public static TripPlanResponse toResponse(Trip trip, List<TripAttraction> tripAttractions) {
        return TripPlanResponse.builder()
                .tripName(trip.getName())
                .tripDestination(trip.getDestination())
                .tripStartDate(trip.getStartDate())
                .tripEndDate(trip.getEndDate())
                .tripAttractions(tripAttractions.stream()
                        .map(TripAttractionResponse::toResponse)
                        .toList())
                .build();
    }
}
