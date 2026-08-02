package org.skunk.git;

import java.io.IOException;
import java.nio.file.Path;

public class Repository {

    private final Path root;
    private final ObjectStore objectStore;
    private final Index index;
    private final Head head;
    private final Refs refs;

    public Repository(Path root) {

        this.root = root;
        
        this.objectStore = new ObjectStore(root);
        this.index = new Index(root);

        head = new Head(root);
        refs = new Refs(root);
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

    public String createCommit(String treeHash, String parentHash, String message) throws IOException {

        Commit commit = Commit.create(treeHash, parentHash, message);

        return objectStore.hash(commit, true);
    }

    /*
    The commit process follows these steps:

    1. The current index (staging area) is converted into a tree object. The tree
    represents the exact state of the staged files at the time of the commit.

    2. The tree object is written to the object database and its SHA-1 hash is
    generated. This hash identifies the snapshot being committed.

    3. The current branch is resolved through HEAD, and the existing commit hash
    for that branch is read as the parent commit.

    4. A commit object is created containing:

        tree <tree hash>
        parent <parent hash>

        <commit message>

    The commit object is then hashed, compressed, and stored in the object
    database.

    5. The branch reference is updated to point to the new commit hash, moving the
    branch forward in history.

    Each commit references its parent commit, creating a directed acyclic graph
    of commits. This graph represents the history of the repository, allowing Git
    to track how the project has evolved over time.

    The resulting commit hash uniquely identifies this commit and represents the
    new state of the repository.

    Unlike a full Git implementation, this simplified version does not yet store
    author information, committer information, timestamps, or support multiple
    parents for merge commits.
    */
    public String commit(String message) throws IOException {

        // Create tree
        Tree tree = Tree.fromIndex(index.read());
        String treeHash = objectStore.hash(tree, true);

        //Find previous commit
        String branch = head.currentBranch();
        String parent = refs.read(branch);

        //Create commit
        Commit commit = Commit.create(treeHash, parent, message);
        String commitHash = objectStore.hash(commit, true);

        //Move branch
        refs.update(branch, commitHash);
        return commitHash;
        
    }

    public void createBranch(String name) throws IOException {

        String currentBranch = head.currentBranch();

        String currentCommit = refs.read(currentBranch);

        refs.create(name, currentCommit);
    }

    public void checkout(String branch) throws IOException {

        if (!refs.exists(branch)) {

            throw new IllegalArgumentException(
                "Branch does not exist: " + branch
            );
        }

        head.updateBranch(branch);
    }

    public ObjectStore getObjectStore() {
        return objectStore;
    }

    public Index getIndex() {
        return index;
    }

    public Head getHead() {
        return head;
    }

    public Refs getRefs() {
        return refs;
    }
    
}
