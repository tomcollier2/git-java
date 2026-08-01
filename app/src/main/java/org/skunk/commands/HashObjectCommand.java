package org.skunk.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.skunk.util.Compression;
import org.skunk.util.SHA1;

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
    public void execute(String[] args) {

        if (args.length < 2) {
            System.out.println("Usage: hash-object <file>");
            return;
        }

        Path file = Path.of(args[1]);

        try {

            byte[] contents = Files.readAllBytes(file);

            String header = "blob " + contents.length  + "\0";
            byte[] headerBytes = header.getBytes();

            byte[] blob = new byte[headerBytes.length + contents.length];

            // Copy both header and contents into a new byte array.
            System.arraycopy(headerBytes, 0, blob, 0, headerBytes.length);
            System.arraycopy(contents, 0, blob, headerBytes.length, contents.length);

            String hash = SHA1.hash(blob);
            byte[] compressed = Compression.compress(blob);

            // Determine object location
            String directory = hash.substring(0, 2);
            String filename = hash.substring(2);

            // Create directories
            Path objectDirectory = Path.of(".skunk", "objects", directory);

            Files.createDirectories(objectDirectory);
            // Write file
            Path objectFile = objectDirectory.resolve(filename);
            Files.write(objectFile, compressed);

            System.out.println(hash);

        } catch (IOException e) {

            System.out.println("Unable to read file.");

        }

    }
    
}
