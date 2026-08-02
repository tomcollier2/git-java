package org.skunk.commands;

import org.skunk.git.Repository;

/*
A branch in Git is not a copy of the repository history. It is simply a named
reference pointing to a commit object within the commit graph.

For example:

    main
     |
     v
    a1b2c3d4  <-- commit

Creating a new branch creates another reference pointing to the current commit:

    main
     |
     v
    a1b2c3d4
     ^
     |
   feature

The command supports two operations:

1. Creating a branch:

   When a branch name is provided, the current commit hash is read from the
   active branch reference and a new branch reference is created pointing to
   the same commit.

2. Listing branches:

   When no branch name is provided, all branch references are displayed. The
   currently checked-out branch is identified by resolving HEAD and marked with
   an asterisk.

*/
public class BranchCommand {

    public void execute(String name) {

        try {
            
            Repository repository = Repository.open();

            repository.createBranch(name);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void execute() {

        try {

            Repository repository = Repository.open();
            String current = repository.getHead().currentBranch();

            for (String branch : repository.getRefs().list()) {

                if (branch.equals(current)) {
                    System.out.println("* " + branch);
                } else {
                    System.out.println("  " + branch);
                }
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

    }
    
}
