package com.partior.service;

import com.partior.exception.OutboundOperationFailed;
import com.partior.model.*;
import com.partior.util.Outbound;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

import static com.partior.util.UtilFunctions.jsonDeserialize;

@Service
public class StarWarsService {

    @Autowired
    Outbound outbound;

    @Value("${swapi.people.endpoint}")
    private String peopleUrl;

    @Value("${swapi.starships.chupul.endpoint}")
    private String starshipUrl;

    @Value("${swapi.planets.endpoint}")
    private String planetsUrl;

    static final Logger LOG = LogManager.getLogger(StarWarsService.class.getName());

    public AttackInfoResponse getAttackDetails(int attackerId, int planetId,
                                               int starshipId) throws OutboundOperationFailed {


        final AttackInfoResponse attackInfoResponse = new AttackInfoResponse();

        //Get the starship used by the attacker, set to empty object when none retrieved.
        final People attacker = invokeSWApi(peopleUrl.concat(String.valueOf(attackerId)), People.class);
        if (Optional.ofNullable(attacker.getStarships()).isPresent() &&
                attacker.getStarships().length > 0) {

            attackInfoResponse.setStarship(new AttackerStarship(
                    invokeSWApi(attacker.getStarships()[0], Starship.class)));
        } else {
            attackInfoResponse.setStarship(new Object());
        }

        //Get the number of crews for the extra starship used by the attacker
        final Starship starship = invokeSWApi(starshipUrl.concat(String.valueOf(starshipId)), Starship.class);
        if (!StringUtils.isBlank(starship.getCrew())) {
            attackInfoResponse.setCrew(
                    Integer.parseInt(starship.getCrew().replaceAll(",", ""))
            );
        }

        //Verify if Princess with ID 5 is still on the planet being attacked.
        final Planet planet = invokeSWApi(planetsUrl.concat(String.valueOf(planetId)), Planet.class);
        attackInfoResponse.setLeiaOnPlanet(
                Arrays.stream(planet.getResidents())
                        .anyMatch(i -> i.contains(peopleUrl.concat("5")))
        );

        return attackInfoResponse;
    }

    public <T> T invokeSWApi(final String url, Class<T> clazz) throws OutboundOperationFailed {
        try {
            return jsonDeserialize(outbound.restCall(url), clazz);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new OutboundOperationFailed();
        }
    }
}
