package org.skunk.git;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Blob extends GitObject {

    private Blob(byte[] data) {
        this.data = data;
    }

    public static Blob fromFile(Path file) throws IOException {

        byte[] contents = Files.readAllBytes(file);

        String header = "blob " + contents.length + "\0";

        byte[] headerBytes = header.getBytes();

        byte[] blob = new byte[headerBytes.length + contents.length];

        System.arraycopy(headerBytes, 0, blob, 0, headerBytes.length);
        System.arraycopy(contents, 0, blob, headerBytes.length, contents.length);

        return new Blob(blob);

    }
    
}
