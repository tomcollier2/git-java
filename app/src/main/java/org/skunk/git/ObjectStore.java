package org.skunk.git;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.skunk.util.Compression;
import org.skunk.util.Decompression;
import org.skunk.util.SHA1;

public class ObjectStore {

    private final Path objects;

    public ObjectStore(Path repositoryRoot) {
        this.objects = repositoryRoot.resolve("objects");
    }

    public String hash(GitObject object, boolean write) throws IOException {

        byte[] data = object.getData();

        String hash = SHA1.hash(data);

        if (write) {
            /* Git stores objects like 5e1c309dae7f... as 
                objects/ 
                    5e/ 
                        1c309dae7f... 
            */
            String directory = hash.substring(0, 2);
            String filename = hash.substring(2);
            
            // Create directories
            Path objectDirectory = objects.resolve(directory);
            Files.createDirectories(objectDirectory);

            // Write the file
            Path objectFile = objectDirectory.resolve(filename);
            Files.write(objectFile, Compression.compress(data));

        }

        return hash;

    }

    public byte[] read(String hash) throws IOException {
        
        String directory = hash.substring(0, 2);
        String filename = hash.substring(2);

        Path objectFile = objects.resolve(directory).resolve(filename);

        byte[] compressed = Files.readAllBytes(objectFile);

        return Decompression.decompress(compressed);
    }
    
}
