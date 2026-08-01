package org.skunk.git;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Blob extends GitObject {

    private Blob(byte[] data) {
        super(data);
    }

    public static Blob fromFile(Path file) throws IOException {

        byte[] contents = Files.readAllBytes(file);

        return fromBytes(contents);

    }

    public static Blob fromBytes(byte[] contents) {

        byte[] blob = buildObject(ObjectType.BLOB, contents);

        return new Blob(blob);

    }
    
}
