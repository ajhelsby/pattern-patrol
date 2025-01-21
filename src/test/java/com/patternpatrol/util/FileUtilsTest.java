package com.patternpatrol.util;

import com.patternpatrol.model.Config;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class FileUtilsTest {

    @Test
    public void testShouldListAllPathsInAGivenBase() throws IOException {
        //Given
        Config config = new Config();
        config.setBasePackage("com.patternpatrol");

        //When
        List<String> results = FileUtils.getAllPackagesAtBase(config);
        //Then
        assertTrue(results.size() > 1);
    }
}
