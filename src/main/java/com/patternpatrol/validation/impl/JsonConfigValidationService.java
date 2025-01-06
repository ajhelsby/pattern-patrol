package com.patternpatrol.validation.impl;

import com.patternpatrol.validation.ConfigValidationService;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;

public class JsonConfigValidationService implements ConfigValidationService {
    private static final String SCHEMA_FILE_LOC = "resources/schema.json";

    @Override
    public void validateConfig(final File configFile) {
        try (FileInputStream schemaStream = new FileInputStream(SCHEMA_FILE_LOC);
             FileInputStream jsonStream = new FileInputStream(configFile)) {

            // Load the schema
            JSONObject rawSchema = new JSONObject(new JSONTokener(schemaStream));
            Schema schema = SchemaLoader.load(rawSchema);

            // Load the JSON
            JSONObject json = new JSONObject(new JSONTokener(jsonStream));

            // Validate JSON against the schema
            schema.validate(json); // Throws ValidationException if invalid
            System.out.println("Validation successful: JSON is valid!");

        } catch (org.everit.json.schema.ValidationException e) {
            System.err.println("Validation failed: " + e.getMessage());
            e.getCausingExceptions().forEach(cause -> System.err.println("  - " + cause.getMessage()));
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
    }
}
