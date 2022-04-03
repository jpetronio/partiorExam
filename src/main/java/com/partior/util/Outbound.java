package com.partior.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.partior.exception.OutboundOperationFailed;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static com.partior.util.UtilFunctions.jsonSerialize;

@Component
public class Outbound {
    static final Logger LOG = LogManager.getLogger(Outbound.class.getName());

    public String restCall(final String url) throws OutboundOperationFailed {
        try {
            final RestTemplate rs = new RestTemplate();
            final String restOutput = rs.getForObject(url, String.class);

            final Map<String, Object> logDetails = new HashMap<>();
            logDetails.put("URL", url);
            logDetails.put("RESPONSE_PAYLOAD", restOutput);
            LOG.info(jsonSerialize(logDetails));
        }catch (final Exception e){
            throw new OutboundOperationFailed();
        }

        return new RestTemplate().getForObject(url, String.class);
    }
}
