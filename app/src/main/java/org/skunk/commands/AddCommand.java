package org.skunk.commands;

import java.nio.file.Path;

import org.skunk.git.Blob;
import org.skunk.git.IndexEntry;
import org.skunk.git.Repository;

public class AddCommand {

    /*
    To mirror git add this command should stage a file by storing its contents
    in the object database and recording an entry in the repository index.

    The file is first converted into a blob object. A blob consists of a header
    and the file contents in the following format:

        "blob <size>\0<contents>"

    The SHA-1 hash of the blob becomes the object's unique identifier. The blob
    is compressed and written to the object database using the same layout as Git:

        .skunk/objects/<first 2 hash characters>/<remaining 38 hash characters>

    For example:

        Hash:
            e69de29bb2d1d6434b8b29ae775ad8c2e48c5391

        Stored as:
            .skunk/objects/e6/9de29bb2d1d6434b8b29ae775ad8c2e48c5391

    Once the object has been written, an index entry is created containing the
    blob's hash and the path to the file. The index acts as the staging area,
    tracking which version of each file will be included in the next commit.
    */
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
