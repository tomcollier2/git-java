package org.skunk.commands;

import org.skunk.git.Repository;

public class CheckoutCommand {

    public void execute(String branch) {

        try {

            Repository repository = Repository.open();

            repository.checkout(branch);
        
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}
