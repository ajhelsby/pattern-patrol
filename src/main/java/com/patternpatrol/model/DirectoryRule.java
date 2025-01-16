package com.patternpatrol.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.patternpatrol.enums.DirectoryPattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DirectoryRule extends LogLevelRule implements RuleType {
    @JsonProperty(required = false)
    private String basePackage;
    private DirectoryPattern pattern;
    @JsonProperty(required = false)
    private String patternArg;
    @JsonProperty(required = false)
    private Set<String> patternArgs;
    @JsonProperty(required = false)
    private DirectoryRule directoriesRule;
    @JsonProperty(required = false)
    private FileRule fileRule;
    @JsonProperty(required = false)
    private Set<String> ignorePackages;
}
