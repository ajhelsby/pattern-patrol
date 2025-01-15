package com.patternpatrol;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patternpatrol.enums.LogLevel;
import com.patternpatrol.model.CheckResult;
import com.patternpatrol.model.Config;
import com.patternpatrol.service.ResultsService;
import com.patternpatrol.service.ValidationService;
import com.patternpatrol.service.impl.JsonConfigValidationService;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@Mojo(name = "check")
public class PatternPatrolPlugin extends AbstractMojo {

    @Parameter(property = "configFile")
    private String configFile;

    @Parameter(property = "failOn", required = false, defaultValue = "ERROR")
    private String failOn;

    public void execute() throws MojoExecutionException {
        try {
            // Parse configuration
            File config = configFile == null ? new File("pattern-patrol.json") : new File(configFile);
            // Validate if the file exists
            if (!config.exists()) {
                throw new FileNotFoundException("Configuration file not found: " + config.getAbsolutePath());
            }

            JsonConfigValidationService configValidationService = new JsonConfigValidationService();
            Config c = configValidationService.validateConfig(config);

            // Validate structure
            ValidationService validationService = new ValidationService();
            LogLevel failOnLevel = LogLevel.fromName(failOn);
            List<CheckResult> results = validationService.validate(c);

            // Check results
            ResultsService resultsService = new ResultsService();
            resultsService.checkResults(results, failOn);// failOnLevel);e
        } catch (Exception e) {
            throw new MojoExecutionException("Failed to validate project structure", e);
        }
    }
}
