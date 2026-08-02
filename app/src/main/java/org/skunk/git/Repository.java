package org.skunk.git;

import java.io.IOException;
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

    public GitObject readObject(String hash) throws IOException {
        return objectStore.read(hash);
    }

    public void add(Path file) throws IOException {

        Blob blob = Blob.fromFile(file);

        String hash =
                objectStore.hash(blob, true);

        index.add(new IndexEntry(hash, file));

    }

    public String writeTree() throws IOException {

        Tree tree = Tree.fromIndex(index.read());

        return objectStore.hash(tree, true);
    }

    public String commitTree(String treeHash, String message) throws IOException {

        Commit commit = Commit.create(treeHash, message);

        return objectStore.hash(commit, true);
    }

    public ObjectStore getObjectStore() {
        return objectStore;
    }

    public Index getIndex() {
        return index;
    }
    
}
