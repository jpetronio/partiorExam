package com.partior.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Starship {

    @JsonProperty("name")
    private String name;

    @JsonProperty("starship_class")
    private String starshipClass;

    @JsonProperty("model")
    private String model;

    private String crew;

}
