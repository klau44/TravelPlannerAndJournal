package pl.coderslab.travelplannerandjournal.model.externalapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeocodeProperties {
    private String city;
    private String country;
    @JsonProperty("place_id")
    private String placeId;
}
