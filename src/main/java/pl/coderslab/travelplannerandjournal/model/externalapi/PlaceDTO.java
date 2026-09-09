package pl.coderslab.travelplannerandjournal.model.externalapi;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceDTO {
    private List<PlaceFeature> features;
}
