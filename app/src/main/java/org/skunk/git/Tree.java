package org.skunk.git;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class Tree extends GitObject {

    private Tree(byte[] contents) {
        super(ObjectType.TREE, contents);
    }

    public static Tree fromBytes(byte[] contents) {
        return new Tree(contents);
    }
    
    public static Tree fromIndex(List<IndexEntry> entries) {

        StringBuilder builder = new StringBuilder();

        for (IndexEntry entry : entries) {

            builder.append("100644 ");

            builder.append(entry.getPath());

            builder.append(" ");

            builder.append(entry.getHash());

            builder.append("\n");

        }

        return fromBytes(
                builder.toString()
                       .getBytes(StandardCharsets.UTF_8)
        );

    }
    
}
