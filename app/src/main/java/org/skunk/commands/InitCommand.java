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
                heads/
            HEAD
    
    The HEAD file should contain:
        ref: refs/heads/main
    */
    public void execute() {

        try {

            Path root = Path.of(".skunk");
            Path objects = root.resolve("objects");
            Path refs = root.resolve("refs");
            Path heads = refs.resolve("heads");
            Path headFile = root.resolve("HEAD");

            Files.createDirectories(objects);
            Files.createDirectories(heads);

            Files.writeString(headFile, "ref: refs/heads/main\n");

            System.out.println("Initialised empty Skunk repository :)");

        } catch (IOException e) {

            System.out.println("Failed to create repository.");
            e.printStackTrace();

        }

    } 
    
}
