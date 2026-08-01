package org.skunk.commands;

import org.skunk.git.Blob;
import org.skunk.git.Repository;
import org.skunk.options.HashObjectOptions;

public class HashObjectCommand {

    /*
    To mirror git hash-object this command should compute the Git object ID (SHA-1 hash)
    for a file.

    Git does not hash the raw file contents directly. It first creates a blob object by
    prepending a header of the form:

        "blob <size>\0"

    where <size> is the number of bytes in the file and '/0' is a null terminator. The
    header and file contents are concatenated into a single byte array, and the SHA-1 hash
    of that array becomes the object's ID.

    If the "-w" option is specified the file is compressed and written into the object database.

    Objects are stored using the first two characters of the SHA-1 hash as a directory name,
    with the remaining 38 characters used as the filename.

    For example, given the hash:

        e69de29bb2d1d6434b8b29ae775ad8c2e48c5391

    the object will be stored as:

        .skunk/objects/e6/9de29bb2d1d6434b8b29ae775ad8c2e48c5391
    */
    public void execute(HashObjectOptions options) {

        if (options.getFile() == null) {
            System.out.println("Usage: skunk hash-object [-t <type>] [-w] <file>");
            return;
        }

        try {

            Blob blob = Blob.fromFile(options.getFile());

            Repository repository = Repository.open();

            String hash = repository.getObjectStore().hash(blob, options.getWrite());

            System.out.println(hash);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
    
}
