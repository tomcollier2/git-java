package org.skunk.git;

import java.nio.file.Path;

/*
Simplified index, that actual git index is a binary file.
*/
public class IndexEntry {
    
    private final String hash;
    private final Path path;

    public IndexEntry(String hash, Path path) {
        this.hash = hash;
        this.path = path;
    }

    public String getHash() {
        return hash;
    }

    public Path getPath() {
        return path;
    }

    @Override
    public String toString() {
        return hash + " " + path;
    }
}
