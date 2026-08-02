package org.skunk.git;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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

    public List<TreeEntry> entries() {

        List<TreeEntry> entries = new ArrayList<>();
        String text = new String(getContents());

        for(String line : text.split("\n")) {

            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split(" ");
            entries.add(new TreeEntry(parts[0], parts[1], parts[2]));
        }

        return entries;
    }
    
}
