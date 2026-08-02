package org.skunk.git;

import java.nio.file.Path;

public class TreeEntry {

    private final String mode;
    private final String name;
    private final String hash;

    public TreeEntry(String mode, String name, String hash) {

        this.mode = mode;
        this.name = name;
        this.hash = hash;
    }

    public IndexEntry toIndexEntry() {

        return new IndexEntry(hash, Path.of(name));
    }

    public String getMode() {
        return mode;
    }

    public String getName() {
        return name;
    }

    public String getHash() {
        return hash;
    }
    
}
