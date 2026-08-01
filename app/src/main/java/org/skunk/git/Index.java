package org.skunk.git;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Index {

    private final Path indexFile;

    public Index(Path repositoryRoot) {

        indexFile = repositoryRoot.resolve("index");

    }

    public void add(IndexEntry entry) throws IOException {

        List<String> lines;

        if (Files.exists(indexFile)) {
            lines = Files.readAllLines(indexFile);
        } else {
            lines = new ArrayList<>();
        }

        lines.add(entry.toString());

        Files.write(indexFile, lines);

    }
    
}
