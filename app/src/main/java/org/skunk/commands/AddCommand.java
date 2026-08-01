package org.skunk.commands;

import java.nio.file.Path;

import org.skunk.git.Repository;

public class AddCommand {

    public void execute(String[] args) {

        if (args.length != 2) {

            System.out.println("Usage: add <file>");
            return;

        }

        try {

            Repository repository = Repository.open();

            repository.add(Path.of(args[1]));

        }
        catch (Exception e) {

            e.printStackTrace();

        }

    }
    
}
