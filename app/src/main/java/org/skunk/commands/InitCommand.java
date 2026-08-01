package org.skunk.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class InitCommand {
    /*
    To mirror git init - InitCommand should create:
        .skunk/
            objects/
            refs/
            HEAD
    
    The HEAD file should contain:
        ref: refs/heads/main
    */
    public void execute() {

        try {

            Path gitDirectory = Path.of(".skunk");

            Files.createDirectories(gitDirectory);

            System.out.println("Repository created!"); 
        } catch (IOException e) {

            System.out.println("Failed to create repository.");
        }

    } 
    
}
