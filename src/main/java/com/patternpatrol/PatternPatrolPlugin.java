package com.patternpatrol;

import com.patternpatrol.enums.LogLevel;
import com.patternpatrol.exception.FileNotFoundException;
import com.patternpatrol.exception.PatternPatrolException;
import com.patternpatrol.model.CheckResult;
import com.patternpatrol.model.Config;
import com.patternpatrol.service.ResultsService;
import com.patternpatrol.service.ValidationService;
import com.patternpatrol.service.impl.JsonConfigValidationService;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.util.List;

@Mojo(name = "verify")
public class PatternPatrolPlugin extends AbstractMojo {

    @Parameter(property = "configFile")
    private String configFile;

    @Parameter(property = "failOn", required = false, defaultValue = "ERROR")
    private String failOn;

    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            // Parse configuration
            getLog().info("Starting Pattern Patrol");
            File config = configFile == null ? new File("pattern-patrol.json") : new File(configFile);
            // Validate if the file exists
            if (!config.exists()) {
                throw new FileNotFoundException("Configuration file not found: " + config.getAbsolutePath());
            }

            getLog().info("Parsing configuration file: " + config.getAbsolutePath());
            JsonConfigValidationService configValidationService = new JsonConfigValidationService();
            Config c = configValidationService.validateConfig(config);
            getLog().info("Configuration file validated successfully");

            // Validate structure
            getLog().info("Attempting to validate project structure");
            ValidationService validationService = new ValidationService();
            LogLevel failOnLevel = failOn == null ? LogLevel.ERROR : LogLevel.fromName(failOn);
            List<CheckResult> results = validationService.validate(c);
            getLog().info("Finished validating project structure");

            // Check results
            getLog().info("Checking results");
            ResultsService resultsService = new ResultsService();
            resultsService.checkResults(results, failOnLevel, getLog());
            getLog().info("Finished checking results");
            getLog().info("Pattern Patrol completed");
        } catch (PatternPatrolException e) {
            getLog().error(e.getMessage());
            throw new MojoFailureException(e.getMessage());
        } catch (Exception e) {
            throw new MojoExecutionException("Error executing plugin: " + e.getMessage(), e);
        }
    }
}
