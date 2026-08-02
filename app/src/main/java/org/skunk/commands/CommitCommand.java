package org.skunk.commands;

import org.skunk.git.Repository;
import org.skunk.options.CommitOptions;

public class CommitCommand {

    /*
    To mirror git commit this command creates a new commit from the currently
    staged changes.

    The commit message is parsed from the command options, then passed to the
    repository which creates the tree and commit objects, stores them in the
    object database, and updates the current branch reference.

    The resulting commit hash is printed after the branch has been updated.
    */
    public void execute(CommitOptions options) {

        try {

            Repository repository = Repository.open();

            String hash = repository.commit(options.getMessage());

            System.out.println(hash);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
