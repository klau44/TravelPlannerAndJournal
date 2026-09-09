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
    private String city;
    private String country;
    private String name;
    private String formatted;
    private List<String> categories;
    private Double lat;
    private Double lon;
}
