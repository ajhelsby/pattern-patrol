package com.patternpatrol.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Config {
    @JsonProperty(required = false)
    private String basePackage;
    @JsonProperty(required = false)
    private FileRule fileRule;
    @JsonProperty(required = false)
    private DirectoryRule directoriesRule;
}
