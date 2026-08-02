package org.skunk.options;

public class CatFileOptions {

    public enum Mode {
        TYPE,
        SIZE,
        PRETTY
    }

    private final Mode mode;
    private final String hash;

    public CatFileOptions(String[] args) {

        if (args.length != 3) {
            throw new IllegalArgumentException(
                    "Usage: cat-file (-t|-s|-p) <hash>"
            );
        }

        hash = args[2];

        switch (args[1]) {

            case "-t":
                mode = Mode.TYPE;
                break;

            case "-s":
                mode = Mode.SIZE;
                break;

            case "-p":
                mode = Mode.PRETTY;
                break;

            default:
                throw new IllegalArgumentException(
                        "Unknown option " + args[1]
                );
        }
    }

    public Mode getMode() {
        return mode;
    }

    public String getHash() {
        return hash;
    }
    
}
