package org.skunk.commands;

import org.skunk.git.GitObject;
import org.skunk.git.Repository;
import org.skunk.options.CatFileOptions;

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

    The command supports the same modes as Git:

        -t    Print the object's type (blob, tree, commit, etc.)
        -s    Print the size of the object's contents in bytes.
        -p    Pretty-print the object's contents.
    */
    public void execute(CatFileOptions options) {

        try {

            Repository repository = Repository.open();

            GitObject object =
                    repository.getObjectStore().read(options.getHash());

            switch (options.getMode()) {

                case TYPE:
                    System.out.println(object.getType());
                    break;
                case SIZE:
                    System.out.println(object.getSize());
                    break;
                case PRETTY:
                    System.out.println(new String(object.getContents()));
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
    
}
