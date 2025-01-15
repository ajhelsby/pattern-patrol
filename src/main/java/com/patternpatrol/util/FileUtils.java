package com.patternpatrol.util;

import com.patternpatrol.model.Config;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.patternpatrol.constant.ModuleConstants.DIRECTORY_DIVISOR_REGEX;
import static com.patternpatrol.constant.ModuleConstants.PACKAGE_DIVISOR_REGEX;

public final class FileUtils {

    private FileUtils() {
    }

    public static List<String> getAllPackagesAtBase(final Config config) throws IOException {
        String base = config.getBasePackage().replace(PACKAGE_DIVISOR_REGEX, DIRECTORY_DIVISOR_REGEX);
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String searchBasePath = s + "/src/main/java/" + base;
        List<String> allPaths = getAllPaths(searchBasePath);
        return allPaths.stream()
                .map(path -> path.replace(searchBasePath + "/", ""))
                .collect(Collectors.toList());
    }

    public static List<String> getAllPaths(final String basePackage) throws IOException {
        List<Path> fileList = new ArrayList<>();
        Path startPath = Paths.get(basePackage);

        Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
                fileList.add(file); // Add file to the list
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(final Path file, final IOException exc) throws IOException {
                System.err.println("Failed to access file: " + file + " due to " + exc.getMessage());
                return FileVisitResult.CONTINUE;
            }
        });

        return fileList.parallelStream().map(Path::toString).collect(Collectors.toList());
    }
}
