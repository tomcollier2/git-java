package org.skunk.git;

public class TreeEntry {

    private final String mode;
    private final String name;
    private final String hash;

    public TreeEntry(String mode, String name, String hash) {

        this.mode = mode;
        this.name = name;
        this.hash = hash;
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
