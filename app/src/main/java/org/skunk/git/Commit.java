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

    public String getParent() {

        String contents = new String(getContents(), StandardCharsets.UTF_8);

        for (String line : contents.split("\n")) {

            if (line.startsWith("parent ")) {

                return line.substring(7);
            }
        }

        return null;
    }

    public String getMessage() {

        String contents = new String(getContents(), StandardCharsets.UTF_8);

        int seperator = contents.indexOf("\n\n");

        if (seperator == -1) {
            return "";
        }

        return contents.substring(seperator + 2);
    }
    
}
