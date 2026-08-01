package org.skunk.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
    */
    public void execute(String[] args) {

        if (args.length < 2) {
            System.out.println("Usage: hash-object <file>");
            return;
        }

        Path file = Path.of(args[1]);

        try {

            byte[] contents = Files.readAllBytes(file);

            System.out.println(new String(contents));

        } catch (IOException e) {

            System.out.println("Unable to read file.");

        }

    }
    
}
