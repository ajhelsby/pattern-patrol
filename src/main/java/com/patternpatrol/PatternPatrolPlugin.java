package com.patternpatrol;

import com.patternpatrol.enums.LogLevel;
import com.patternpatrol.exception.PatternPatrolException;
import com.patternpatrol.model.Config;
import com.patternpatrol.validation.ValidationService;
import com.patternpatrol.validation.impl.JsonConfigValidationService;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

@Mojo(name = "check")
public class PatternPatrolPlugin extends AbstractMojo {

    @Parameter(property = "configFile", required = false)
    private File configFileLoc;

    @Parameter(property = "failOn", required = false)
    private LogLevel failOn;

    public void execute() throws MojoExecutionException {
        try {
            // Parse configuration
            File configFile = configFileLoc == null ? new File("pattern-patrol.json") : configFileLoc;
            JsonConfigValidationService configValidationService = new JsonConfigValidationService();
            configValidationService.validateConfig(configFile);
            ObjectMapper mapper = new ObjectMapper();
            Config config = mapper.readValue(configFile, Config.class);

            // Validate structure
            ValidationService validationService = new ValidationService();
            validationService.validate(config, failOn);
        } catch (PatternPatrolException ppe) {
            throw new MojoExecutionException("Failed to validate project structure", ppe);
        } catch (Exception e) {
            throw new MojoExecutionException("Failed to validate project structure", e);
        }
    }
}
