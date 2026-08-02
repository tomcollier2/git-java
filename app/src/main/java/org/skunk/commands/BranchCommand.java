package org.skunk.commands;

import org.skunk.git.Repository;

public class BranchCommand {

    public void execute(String name) {

        try {
            
            Repository repository = Repository.open();

            repository.createBranch(name);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
