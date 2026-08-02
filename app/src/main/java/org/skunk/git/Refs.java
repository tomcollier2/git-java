package org.skunk.git;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/*
Represents the repository references (refs).

In Git, branch references store the SHA-1 hash of the commit currently pointed
to by each branch. These references are stored under:

    .skunk/refs/heads/<branch name>

For example, the main branch reference:

    .skunk/refs/heads/main

may contain:

    a1b2c3d4e5f678901234567890abcdef12345678

where the value is the SHA-1 hash of the latest commit on that branch.

This class provides methods to read and update branch references. Reading a
reference resolves a branch name to the commit hash it currently points to,
while updating a reference moves the branch pointer to a new commit.

If a branch reference does not exist, read() returns null.

Together with HEAD, refs allow the repository to track the currently checked-out
branch and the commit history associated with each branch.
*/
public class Refs {

    private final Path headsDirectory;

    public Refs(Path repositoryRoot) {

        headsDirectory = repositoryRoot.resolve("refs").resolve("heads");
    }

    public String read(String branch) throws IOException {

        Path file = headsDirectory.resolve(branch);

        if (!Files.exists(file)) {
            return null;
        }

        return Files.readString(file).trim();
    }

    public void update(String branch, String hash) throws IOException {

        Files.writeString(headsDirectory.resolve(branch), hash);
    }

    public void create(String branch, String hash) throws IOException {

        Path branchFile = headsDirectory.resolve(branch);

        if (Files.exists(branchFile)) {

            throw new IllegalArgumentException(
                "Branch already exists: " + branch
            );
        }

        Files.writeString(branchFile, hash);
    }

    public List<String> list() throws IOException {

        return Files.list(headsDirectory)
            .map(path -> path.getFileName().toString()).toList();
    }

    public boolean exists(String branch) {

        return Files.exists(headsDirectory.resolve(branch));
    }
    
}
