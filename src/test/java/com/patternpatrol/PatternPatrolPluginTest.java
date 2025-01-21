package com.patternpatrol;


import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.testing.MojoRule;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PatternPatrolPluginTest {

    @Rule
    public MojoRule rule = new MojoRule();

    @Test
    public void testShouldPassAllChecks() throws Exception {
        // Given
        File pom = new File("src/test/resources/pom.xml");
        assertNotNull(pom);
        assertTrue(pom.exists());

        // When
        PatternPatrolPlugin mojo = (PatternPatrolPlugin) rule.lookupMojo("verify", pom);
        assertNotNull(mojo);

        // Then
        assertDoesNotThrow(() -> mojo.execute());
    }

    @Test
    public void testShouldPassAllChecksFor1LayerDeep() throws Exception {
        // Given
        File pom = new File("src/test/resources/pom-simple.xml");
        assertNotNull(pom);
        assertTrue(pom.exists());

        // When
        PatternPatrolPlugin mojo = (PatternPatrolPlugin) rule.lookupMojo("verify", pom);
        assertNotNull(mojo);

        // Then
        assertDoesNotThrow(() -> mojo.execute());
    }

    @Test
    public void testShouldFailDueToIncorrectSchema() throws Exception {
        // Given
        File pom = new File("src/test/resources/pom_incorrect_config.xml");
        assertNotNull(pom);
        assertTrue(pom.exists());

        // When
        PatternPatrolPlugin mojo = (PatternPatrolPlugin) rule.lookupMojo("verify", pom);
        assertNotNull(mojo);

        // Then
        assertThrows(MojoFailureException.class, () -> mojo.execute());
    }
}
