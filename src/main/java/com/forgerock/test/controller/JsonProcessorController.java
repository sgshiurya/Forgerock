package com.forgerock.test.controller;

import com.forgerock.test.data.JSONProcessData;
import com.forgerock.test.service.JSONProcessOrchestrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/json/processor")
public class JsonProcessorController {

    @Autowired
    private JSONProcessOrchestrator jsonProcessor;

    @PostMapping(value = "/process", produces = MediaType.APPLICATION_JSON_VALUE)
    public String process(@RequestBody @Valid JSONProcessData jsonProcessData) {
        return jsonProcessor.process(jsonProcessData.getConfig(),
                jsonProcessData.getPayload());
  }
}
