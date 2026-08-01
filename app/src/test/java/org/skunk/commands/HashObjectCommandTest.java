package org.skunk.commands;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class HashObjectCommandTest {

    @Test
    void hashObjectMatchesGit() throws Exception {

        Path file = Path.of("hello.txt");

        Files.writeString(file, "Hello World");


        // Run git hash-object hello.txt
        Process git = new ProcessBuilder(
                "git",
                "hash-object",
                "hello.txt"
        )
        .redirectErrorStream(true)
        .start();

        String gitOutput;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(git.getInputStream())
        )) {
            gitOutput = reader.lines()
                    .collect(Collectors.joining());
        }

        // Run skunk hash-object command with hello.txt
        Process skunk = new ProcessBuilder(
                "java",
                "-cp",
                "build/classes/java/main",
                "org.skunk.App",
                "hash-object",
                "hello.txt"
        )
        .redirectErrorStream(true)
        .start();

        String skunkOutput;
        
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(skunk.getInputStream())
        )) {
            skunkOutput = reader.lines()
            .collect(Collectors.joining());
        }
        
        // Compare outputs
        assertEquals(gitOutput, skunkOutput);


        Files.deleteIfExists(file);
    }
}