package com.forgerock.test.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.forgerock.test.data.JSONProcessData;
import com.schibsted.spt.data.jslt.Expression;

public interface JSTLService {

    Expression createJSTL(final JSONProcessData.FeatureConfig featureConfig, JsonNode node);

    String transformJsonFeed(final Expression expression, final String inputStr);

}
