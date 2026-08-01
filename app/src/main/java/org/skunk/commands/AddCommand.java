package org.skunk.commands;

import java.nio.file.Path;

import org.skunk.git.Blob;
import org.skunk.git.IndexEntry;
import org.skunk.git.Repository;

public class AddCommand {

    public void execute(String[] args) {

        if (args.length != 2) {

            System.out.println("Usage: add <file>");
            return;

        }

        try {

            Repository repository = Repository.open();

            Path file = Path.of(args[1]);

            Blob blob = Blob.fromFile(file);

            String hash =
                    repository
                    .getObjectStore()
                    .hash(blob, true);

            repository
                    .getIndex()
                    .add(new IndexEntry(hash, file));

        }
        catch (Exception e) {

            e.printStackTrace();

        }

    }
    
}
