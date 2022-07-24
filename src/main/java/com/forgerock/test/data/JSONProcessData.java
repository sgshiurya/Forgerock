package com.forgerock.test.data;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class JSONProcessData {

    @NotNull(message = "Config must not be empty")
    private FeatureConfig config;

    @NotNull(message = "Payload must not be empty")
    private JsonNode payload;

    @Data
    public static class FeatureConfig {

        private Integer id;

        private String name;

        @Valid
        @NotNull
        private List<Transform> transforms;
    }

    @Data
    public static class Transform {

        @NotBlank(message = "Transformer name must not be empty")
        private String name;

        private boolean useInML;

        private boolean enabled = false;

        @NotBlank(message = "JSTL expression must not be empty")
        private String jsltExpression;

    }
}
