package org.skunk.commands;

import org.skunk.git.Repository;

public class CheckoutCommand {
    /*
    To mirror git checkout this command should switch the repository to a different
    branch.

    The command first verifies that the requested branch exists. It then reads the
    commit currently referenced by that branch and restores the working directory
    to match the snapshot recorded in the commit's tree object.

    The checkout process follows these steps:

    1. Verify that the requested branch reference exists.

    2. Read the commit hash stored in the branch reference.

    3. Read the commit object and obtain the hash of its tree object.

    4. Restore the working directory by reading each blob referenced by the tree
    and writing its contents back to the corresponding file.

    5. Update HEAD so that it points to the newly checked-out branch.

    After checkout completes, the working directory reflects the state of the
    selected branch, and any future commits will advance that branch.

    This simplified implementation assumes that the working directory can be safely
    overwritten. It does not yet detect uncommitted changes or support checking out individual commits
    (detached HEAD).
    */
    public void execute(String branch) {

        try {

            Repository repository = Repository.open();

            repository.checkout(branch);
        
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}
