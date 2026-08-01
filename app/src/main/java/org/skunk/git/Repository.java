package org.skunk.git;

import java.nio.file.Path;

public class Repository {

    private final Path root;

    public Repository() {
        this.root = Path.of(".skunk");
    }

    public ObjectStore getObjectStore() {
        return new ObjectStore(root);
    }
    
}
