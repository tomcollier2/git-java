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

        boolean found = false;

        for (int i = 0; i < lines.size(); i++) {

            IndexEntry existing = IndexEntry.parse(lines.get(i));

            if (existing.getPath().equals(entry.getPath())) {

                lines.set(i, entry.toString());
                found = true;
                break;
            }
        }

        if(!found) {
            lines.add(entry.toString());
        }

        /*
        In real Git, the index is memory-mapped and rewritten as a binary structure for performance.
        */
        Files.write(indexFile, lines);

    }

    public List<IndexEntry> read() throws IOException {

        List<IndexEntry> entries = new ArrayList<>();

        if (Files.exists(indexFile)) {
                
            for (String line : Files.readAllLines(indexFile)) {

                entries.add(
                    IndexEntry.parse(line)
                );

            }
        }
        
        return entries;
    }
    
}
