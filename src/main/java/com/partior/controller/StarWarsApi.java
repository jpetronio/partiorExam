package com.partior.controller;

import com.partior.exception.OutboundOperationFailed;
import com.partior.model.AttackInfoResponse;
import com.partior.service.StarWarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/api")
public class StarWarsApi {

    @Autowired
    private StarWarsService service;

    /**
     * <p>
     * <p>
     * Used to retrieve attack Information of Dart Vader to the Planet Alderan
     * </p>
     *
     * @return Java Object
     * @throws Exception Outbound Operation exception
     */
    @GetMapping(value = "/information")
    public ResponseEntity<AttackInfoResponse> getDarthVaderAttackDetails() throws OutboundOperationFailed {

        final int deathVaderId = 4;
        final int alderanId = 2;
        int deathStarId = 9;
        return new ResponseEntity<>(service.getAttackDetails(deathVaderId, alderanId, deathStarId), HttpStatus.OK);

    }

    /**
     * <p>
     * Used to retrieve attack Information using parameters
     * made just for api flexibility for future use.
     * </p>
     *
     * @param starshipId extra starship of the attacker.
     * @param attackerId the planet attacker.
     * @param planetId   planet to be attacked
     * @return Java Object
     * @throws Exception Outbound Operation exception
     */
    @GetMapping(value = "/information2")
    public ResponseEntity<AttackInfoResponse> getAttackDetails(@RequestParam(value = "attackerId") int attackerId,
                                                               @RequestParam(value = "planetId") int planetId,
                                                               @RequestParam(value = "starshipId") int starshipId
    ) throws OutboundOperationFailed {
        return new ResponseEntity<>(service.getAttackDetails(attackerId, planetId, starshipId), HttpStatus.OK);

    }
}
