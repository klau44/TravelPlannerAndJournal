package pl.coderslab.travelplannerandjournal.model.dto;

import lombok.*;
import pl.coderslab.travelplannerandjournal.model.entity.Trip;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTripResponse {

    private Long id;
    private String name;
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;

    public static CreateTripResponse toResponse(Trip trip) {
        return CreateTripResponse.builder()
                .id(trip.getId())
                .name(trip.getName())
                .destination(trip.getDestination())
                .startDate(trip.getStartDate())
                .endDate(trip.getEndDate())
                .build();
    }
}
