package pl.coderslab.travelplannerandjournal.model.externalapi;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeocodeFeature {
    private GeocodeProperties properties;
}
