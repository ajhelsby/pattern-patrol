package com.patternpatrol.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patternpatrol.exception.PatternPatrolException;
import com.patternpatrol.model.Config;
import com.patternpatrol.service.ConfigValidationService;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;

public class JsonConfigValidationService implements ConfigValidationService {
    private static final String SCHEMA_FILE_LOC = "src/main/resources/schema.json";

    @Override
    public Config validateConfig(final File configFile) throws PatternPatrolException {
        try (FileInputStream schemaStream = new FileInputStream(SCHEMA_FILE_LOC);
             FileInputStream jsonStream = new FileInputStream(configFile)) {

            // Load the schema
            JSONObject rawSchema = new JSONObject(new JSONTokener(schemaStream));
            Schema schema = SchemaLoader.load(rawSchema);

            // Load the JSON
            JSONObject json = new JSONObject(new JSONTokener(jsonStream));

            // Validate JSON against the schema
            schema.validate(json);
            // Throws ValidationException if invalid
            System.out.println("Validation successful: JSON is valid!");

            ObjectMapper mapper = new ObjectMapper();
            return  mapper.readValue(Files.newInputStream(configFile.toPath()), Config.class);

        } catch (org.everit.json.schema.ValidationException e) {
            e.getCausingExceptions().forEach(cause -> System.err.println("  - " + cause.getMessage()));
            throw new PatternPatrolException("Validation failed: " + e.getMessage());
        } catch (Exception e) {
            throw new PatternPatrolException("Error occurred: " + e.getMessage());
        }
    }
}
