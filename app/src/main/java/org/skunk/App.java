package org.skunk;

import org.skunk.commands.InitCommand;

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

            default:
                System.out.println("Unknown command :(");
        }
    }
}
