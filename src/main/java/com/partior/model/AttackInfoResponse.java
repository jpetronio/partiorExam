package com.partior.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AttackInfoResponse {

    @JsonProperty("starship")
    private Object starship;

    @JsonProperty("crew")
    private int crew;

    @JsonProperty("isLeiaOnPlanet")
    private boolean isLeiaOnPlanet;

    public AttackInfoResponse(final Starship s) {
        this.starship = s;

    }

}


