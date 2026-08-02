package org.skunk.options;

public class CommitTreeOptions {

    private final String treeHash;
    private final String message;

    public CommitTreeOptions(String[] args) {

        if (args.length != 4) {
            throw new IllegalArgumentException(
                    "Usage: commit-tree <tree> -m <message>"
            );
        }

        treeHash = args[1];

        if (!args[2].equals("-m")) {
            throw new IllegalArgumentException(
                    "Expected -m"
            );
        }

        message = args[3];

    }

    public String getTreeHash() {
        return treeHash;
    }

    public String getMessage() {
        return message;
    }
    
}
