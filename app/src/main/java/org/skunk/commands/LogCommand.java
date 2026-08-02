package org.skunk.commands;

import org.skunk.git.Commit;
import org.skunk.git.GitObject;
import org.skunk.git.Repository;

public class LogCommand {
    /*
    To mirror git log this command should display the commit history of the
    currently checked-out branch.

    The command begins by resolving HEAD to find the current branch, then reads the
    branch reference to obtain the latest commit hash.

    Each commit object contains a reference to its parent commit:

        commit
        |
        v
        parent commit
        |
        v
        parent commit
        |
        v
        ...

    The command follows these parent references backwards through the commit history,
    printing each commit hash and message as it goes.

    Because commits reference previous commits, the repository history forms a
    directed acyclic graph (DAG) of commits. This command walks backwards through
    that graph along the current branch's history.

    The traversal stops when there is no parent commit remaining, which indicates
    the first commit in the repository history.

    This simplified implementation only follows the first parent of each commit and
    does not yet support merge commits with multiple parents.
    */
    public void execute() {

        try {
            
            Repository repository = Repository.open();

            String hash = repository.getRefs()
                .read(repository.getHead().currentBranch());

            while (hash != null && !hash.isEmpty()) {

                GitObject object = repository.readObject(hash);
                Commit commit = (Commit) object;

                System.out.println("commit " + hash);
                System.out.println(commit.getMessage());

                System.out.println();
                hash = commit.getParent();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
