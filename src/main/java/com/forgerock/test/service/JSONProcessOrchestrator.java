package com.forgerock.test.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.forgerock.test.data.JSONProcessData;
import com.schibsted.spt.data.jslt.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JSONProcessOrchestrator {

    @Autowired
    private JSTLService jstlService;

    public String process(final JSONProcessData.FeatureConfig featureConfig,
                          final JsonNode input) {

        Expression expression = jstlService.createJSTL(featureConfig, input);

        return jstlService.transformJsonFeed(expression,
                input.toString());

    }
}
