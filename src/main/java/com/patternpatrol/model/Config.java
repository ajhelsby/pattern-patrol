package com.patternpatrol.model;

public class Config {
    @JsonProperty("base_package")
    private String basePackage;
    private FileRule files;
    private DirectoryRule directories;
}
