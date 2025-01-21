package com.patternpatrol.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.patternpatrol.enums.LogLevel;
import lombok.Getter;

@Getter
public class LogLevelRule {
    @JsonProperty(required = false)
    private LogLevel level = LogLevel.ERROR;
}
