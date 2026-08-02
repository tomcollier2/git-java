package org.skunk.git;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/*
Represents the repository HEAD reference.

In Git, HEAD points to the currently checked-out branch. Rather than storing
a commit hash directly, HEAD normally contains a reference to a branch:

    ref: refs/heads/<branch name>

For example:

    ref: refs/heads/main

The HEAD file is located at:

    .skunk/HEAD

This class reads the HEAD file and resolves the current branch name by removing
the reference prefix and extracting the final path component.

For example:

    ref: refs/heads/main

becomes:

    main

This implementation only supports a symbolic HEAD reference. Detached HEAD
states, where HEAD contains a commit hash directly instead of a branch reference,
are not currently supported.
*/
public class Head {

    private final Path headFile;

    public Head(Path repositoryRoot) {

        headFile = repositoryRoot.resolve("HEAD");

    }

    public String currentBranch() throws IOException {

        String line = Files.readString(headFile).trim();

        if (!line.startsWith("ref: ")) {
            throw new IllegalStateException("Detached HEAD not supported.");
        }

        return Path.of(line.substring(5)).getFileName().toString();
    }

    public void updateBranch(String branch) throws IOException {

        Files.writeString(headFile, "ref: refs/heads/" + branch + "\n");
    }
    
}
