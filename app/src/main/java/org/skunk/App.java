package org.skunk;

import org.skunk.commands.AddCommand;
import org.skunk.commands.BranchCommand;
import org.skunk.commands.CatFileCommand;
import org.skunk.commands.CommitCommand;
import org.skunk.commands.CommitTreeCommand;
import org.skunk.commands.HashObjectCommand;
import org.skunk.commands.InitCommand;
import org.skunk.commands.LogCommand;
import org.skunk.commands.WriteTreeCommand;
import org.skunk.options.CatFileOptions;
import org.skunk.options.CommitOptions;
import org.skunk.options.CommitTreeOptions;
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

            case "cat-file":
                new CatFileCommand().execute(new CatFileOptions(args));
                break;

            case "add":
                new AddCommand().execute(args);
                break;

            case "write-tree":
                new WriteTreeCommand().execute();
                break;

            case "commit-tree":
                new CommitTreeCommand().execute(new CommitTreeOptions(args));
                break;

            case "commit":
                new CommitCommand().execute(new CommitOptions(args));
                break;
            
            case "log":
                new LogCommand().execute();
                break;

            case "branch":
                BranchCommand command = new BranchCommand();

                if (args.length == 1) {
                    command.execute();
                } else {
                    command.execute(args[1]);
                }
                break;

            default:
                System.out.println("Unknown command :(");
        }
    }
}
