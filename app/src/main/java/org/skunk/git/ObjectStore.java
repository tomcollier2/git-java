package org.skunk.git;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.skunk.util.Compression;
import org.skunk.util.SHA1;

public class ObjectStore {

    private final Path objects;

    public ObjectStore(Path repositoryRoot) {
        this.objects = repositoryRoot.resolve("objects");
    }

    public String write(GitObject object) throws IOException {

        byte[] data = object.getData();

        String hash = SHA1.hash(data);

        String directory = hash.substring(0, 2);
        String filename = hash.substring(2);

        Path objectDirectory = objects.resolve(directory);

        Files.createDirectories(objectDirectory);

        Path objectFile = objectDirectory.resolve(filename);

        Files.write(objectFile, Compression.compress(data));

        return hash;

    }
    
}
