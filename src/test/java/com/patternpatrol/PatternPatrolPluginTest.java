package com.patternpatrol;


import org.apache.maven.plugin.testing.MojoRule;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PatternPatrolPluginTest {

    @Rule
    public MojoRule rule = new MojoRule();

    @Test
    public void testShouldPass() throws Exception {
        // Given
        File pom = new File("src/test/resources/pom.xml");
        assertNotNull(pom);
        assertTrue(pom.exists());

        // When
        PatternPatrolPlugin mojo = (PatternPatrolPlugin) rule.lookupMojo("check", pom);
        assertNotNull(mojo);

        // Then
        assertDoesNotThrow(() -> mojo.execute());
    }
}
