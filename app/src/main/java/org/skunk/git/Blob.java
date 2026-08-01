package org.skunk.git;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Blob extends GitObject {

    private Blob(byte[] contents) {
        super(ObjectType.BLOB, contents);
    }

    public static Blob fromFile(Path file) throws IOException {

        byte[] contents = Files.readAllBytes(file);

        return fromBytes(contents);

    }

    public static Blob fromBytes(byte[] contents) {

        return new Blob(contents);

    }
    
}
