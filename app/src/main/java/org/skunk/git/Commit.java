package org.skunk.git;

import java.nio.charset.StandardCharsets;

public class Commit extends GitObject {

    private Commit(byte[] contents) {
        super(ObjectType.COMMIT, contents);
    }

    public static Commit fromBytes(byte[] contents) {
        return new Commit(contents);
    }

    public static Commit create(
        String treeHash,
        String parentHash,
        String message
    ) {

        StringBuilder builder = new StringBuilder();

        builder.append("tree ").append(treeHash).append("\n");

        if (parentHash != null) {
            builder.append("parent ").append(parentHash).append("\n");
        }
        builder.append("\n");

        builder.append(message);

        return fromBytes(builder.toString().getBytes(StandardCharsets.UTF_8));

    }
    
}
