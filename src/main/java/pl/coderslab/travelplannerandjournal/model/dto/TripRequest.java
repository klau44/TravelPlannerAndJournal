package pl.coderslab.travelplannerandjournal.model.dto;

import jakarta.validation.constraints.Future;
import lombok.*;
import pl.coderslab.travelplannerandjournal.validation.ValidTripDates;

import java.time.LocalDate;

@ValidTripDates
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripRequest {

    private String name;
    private String destination;
    @Future(message = "Trip start date must be in the future")
    private LocalDate startDate;
    @Future(message = "Trip end date must be in the future")
    private LocalDate endDate;
}
