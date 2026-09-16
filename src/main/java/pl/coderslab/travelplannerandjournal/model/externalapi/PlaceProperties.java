package pl.coderslab.travelplannerandjournal.model.externalapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceProperties {
    private String name;
    private String country;
    private String city;
    private String postcode;
    @JsonProperty("address_line2")
    private String address;
    private List<String> categories;
    @JsonProperty("place_id")
    private String externalId;
}
