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

    public static IndexEntry parse(String line) {

        String[] parts = line.split(" ", 2);

        return new IndexEntry(parts[0], Path.of(parts[1]));
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
