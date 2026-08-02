package org.skunk.options;

public class CommitOptions {

    private final String message;

    public CommitOptions(String[] args) {

        if (args.length != 3) {
            throw new IllegalArgumentException("Usage: commit -m <message>");
        }
        
        if (!args[1].equals("-m")) {
            throw new IllegalArgumentException("Expected -m");
        }

        message = args[2];
    }

    public String getMessage() {
        return message;
    }

}
