package org.skunk.commands;

import org.skunk.git.Repository;
import org.skunk.options.CommitTreeOptions;

public class CommitTreeCommand {
    /*
    To mirror git commit-tree this command should create a commit object from an
    existing tree object.

    A tree object represents the state of the repository at a particular point in
    time, while a commit records that snapshot in the project's history. The command
    takes the SHA-1 hash of a tree object together with a commit message and builds
    a commit object containing both.

    In this simplified implementation, the commit has the format:

        tree <tree hash>

        <commit message>

    The commit object is then prefixed with the standard Git object header:

        "commit <size>\0"

    Its SHA-1 hash is computed, the object is compressed and written into the object
    database, and the resulting hash is printed. This hash uniquely identifies the
    commit and can later be used to reference the recorded snapshot.

    Unlike a full Git implementation, this command does not yet record parent
    commits, author information, committer information, or timestamps.
    */
    public void execute(CommitTreeOptions options) {

        try {

            Repository repository =
                    Repository.open();

            String hash =
                    repository.commitTree(
                            options.getTreeHash(),
                            options.getMessage()
                    );

            System.out.println(hash);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
    
}
