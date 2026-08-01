package org.skunk;

import org.skunk.commands.HashObjectCommand;
import org.skunk.commands.InitCommand;
import org.skunk.options.HashObjectOptions;

public class App {

    public static void main(String[] args) {
        
        if (args.length == 0) {

            System.out.println("Usage: skunk <command>");
            return;
        }

        switch (args[0]) {

            case "init":
                new InitCommand().execute();
                break;

            case "hash-object":
                new HashObjectCommand().execute(new HashObjectOptions(args));
                break;

            default:
                System.out.println("Unknown command :(");
        }
    }
}
