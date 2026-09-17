package pl.coderslab.travelplannerandjournal.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripAttractionResponse {

    private Long id;
    private String attractionName;
    private String attractionAddress;
    private boolean visited;

    public static TripAttractionResponse toResponse(TripAttraction tripAttraction) {
        return TripAttractionResponse.builder()
                .id(tripAttraction.getId())
                .attractionName(tripAttraction.getAttraction().getName())
                .attractionAddress(tripAttraction.getAttraction().getAddress())
                .visited(tripAttraction.isVisited())
                .build();
    }
}
