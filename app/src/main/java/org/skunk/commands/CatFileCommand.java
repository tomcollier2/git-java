package org.skunk.commands;

import org.skunk.git.Repository;

public class CatFileCommand {

    /*
    To mirror git cat-file this command should retrieve and display the contents
    of a stored Git object.

    Objects are identified by their SHA-1 hash. The first two characters of the hash
    are used as the directory name, and the remaining 38 characters are used as the
    filename:

        .skunk/objects/<first 2 hash characters>/<remaining 38 hash characters>

    For example, an object with the hash:

        e69de29bb2d1d6434b8b29ae775ad8c2e48c5391

    is stored as:

        .skunk/objects/e6/9de29bb2d1d6434b8b29ae775ad8c2e48c5391

    The object file is stored compressed and contains both a header and the original
    contents. Before displaying the contents, the object must be read from storage,
    decompressed, and the header must be removed.

    The object header follows the format:

        "<type> <size>\0<contents>"

    For example:

        "blob 12\0Hello World!"

    The command locates the null terminator separating the header from the contents,
    discards everything before it, and prints the remaining bytes.
    */
    public void execute(String[] args) {

        if (args.length != 2) {
            System.out.println("Usage: cat-file <hash>");
            return;
        }

        try {

            Repository repository = new Repository();

            byte[] object =
                    repository.getObjectStore().read(args[1]);

            int start = 0;

            while (object[start] != 0) {
                start++;
            }

            start++;

            byte[] contents =
                    new byte[object.length - start];

            System.arraycopy(
                    object,
                    start,
                    contents,
                    0,
                    contents.length
            );

            System.out.println(new String(contents));

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
    
}
