package org.skunk.commands;

import org.skunk.git.Repository;

public class WriteTreeCommand {
    
    /*
    To mirror git write-tree this command should create a tree object from the
    current contents of the repository index.

    The index represents the staging area, where each entry records the path to a
    file together with the SHA-1 hash of its corresponding blob object. The command
    reads every staged entry and constructs a tree object describing the current
    directory structure.

    In this simplified implementation, each tree entry is written in the form:

        <mode> <path> <blob hash>

    For example:

        100644 hello.txt e965047ad7c57865823c7d992b1d046ea66edf78
        100644 readme.md 557db03de997c86a4a028e1ebd3a1ceb225be238

    The completed tree object is then prefixed with the standard Git object header:

        "tree <size>\0"

    and its SHA-1 hash is computed. The compressed tree object is written into the
    object database using the same layout as all other Git objects:

        .skunk/objects/<first 2 hash characters>/<remaining 38 hash characters>

    The resulting SHA-1 hash uniquely identifies the state of the staged files and
    can later be referenced by a commit object.
    */
    public void execute() {

        try {

            Repository repository =
                    Repository.open();

            System.out.println(
                    repository.writeTree()
            );

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
