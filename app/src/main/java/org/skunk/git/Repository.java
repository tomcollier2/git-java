package org.skunk.git;

import java.nio.file.Path;

public class Repository {

    private final Path root;

    private final ObjectStore objectStore;

    private final Index index;

    public Repository(Path root) {

        this.root = root;
        
        this.objectStore = new ObjectStore(root);
        this.index = new Index(root);
    }

    public static Repository open() {
        return new Repository(Path.of(".skunk"));
    }

    public ObjectStore getObjectStore() {
        return objectStore;
    }

    public Index getIndex() {
        return index;
    }
    
}
