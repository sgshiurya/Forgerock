package com.forgerock.test.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.forgerock.test.data.JSONProcessData;
import com.forgerock.test.service.JSTLService;
import com.schibsted.spt.data.jslt.Expression;
import com.schibsted.spt.data.jslt.Parser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.forgerock.test.constants.JsonConstants.*;

@Service
@Slf4j
public class DefaultJSTLService implements JSTLService {

    public Expression createJSTL(final JSONProcessData.FeatureConfig featureConfig,
                                 final JsonNode node) {

        StringBuilder sbr = new StringBuilder(curlyBraceStart);

        if (node.get(eventId) != null) {
            sbr.append(eventIdStr)
                    .append(colon)
                    .append(node.get(eventId))
                    .append(comma);
        }

        featureConfig.getTransforms()
                .stream()
                .filter(transform -> transform.isEnabled())
                .filter(transform -> Optional.ofNullable(transform.getName()).isPresent())
                .filter(transform -> Optional.ofNullable(transform.getJsltExpression()).isPresent())
                .forEach( transform -> {
                    sbr.append(quote)
                            .append(transform.getName())
                            .append(quote)
                            .append(colon)
                            .append(transform.getJsltExpression())
                            .append(comma);

                });

        sbr.append(curlyBraceEnd);

        return Parser.compileString(sbr.toString());
    }

    public String transformJsonFeed(final Expression expression,
                                    final String inputJson) {

        if (StringUtils.isBlank(inputJson)) {
            log.info("transformJsonFeed inputJson is empty");
            return null;
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode input = mapper.readTree(inputJson);
            JsonNode output = expression.apply(input);
            return output.toString();
        } catch (Exception exception) {
            log.error("Error processing input json", exception);
        }
        return null;
    }

}
