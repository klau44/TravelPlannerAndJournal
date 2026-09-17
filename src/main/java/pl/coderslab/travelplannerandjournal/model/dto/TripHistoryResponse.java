package pl.coderslab.travelplannerandjournal.model.dto;

import lombok.*;
import pl.coderslab.travelplannerandjournal.model.entity.Trip;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripHistoryResponse {

    private Long id;
    private String name;
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private String journalEntry;
    private List<String> attractions;

    public static TripHistoryResponse toResponse(Trip trip) {
        return TripHistoryResponse.builder()
                .id(trip.getId())
                .name(trip.getName())
                .destination(trip.getDestination())
                .startDate(trip.getStartDate())
                .endDate(trip.getEndDate())
                .journalEntry(trip.getJournalEntry().getContent())
                .attractions(trip.getTripAttractions().stream()
                        .map(tripAttraction -> tripAttraction.getAttraction().getName())
                        .toList())
                .build();
    }
}
