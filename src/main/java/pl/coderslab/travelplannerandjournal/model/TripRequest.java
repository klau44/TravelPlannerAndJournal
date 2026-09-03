package pl.coderslab.travelplannerandjournal.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripRequest {

    private String name;
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
}
