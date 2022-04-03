package com.partior.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttackerStarship {

    @JsonProperty("name")
    private String name;

    @JsonProperty("class")
    private String starshipClass;

    @JsonProperty("model")
    private String model;

    public AttackerStarship(final Starship starship){
        this.name = starship.getName();
        this.starshipClass = starship.getStarshipClass();
        this.model = starship.getModel();
    }
}
