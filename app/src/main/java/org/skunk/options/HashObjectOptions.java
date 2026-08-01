package org.skunk.options;

import java.nio.file.Path;

public class HashObjectOptions {

    private enum Type { COMMIT, TREE, BLOB, TAG; };

    private boolean write = false; 
    private Path file = null; 
    private Type type = Type.BLOB; 
    
    public HashObjectOptions(String[] args) { 

        if (args.length == 2) {

            file = Path.of(args[1]); 

        } else if (args.length == 3 && args[1].equals("-w")) {

            write = true; 
            file = Path.of(args[2]); 

        } else if (args.length == 4 && args[1].equals("-t")) { 

            parseType(args[2]); 
            if (type != null) { 
                file = Path.of(args[3]); 
            } 

        } else if (args.length == 5 && args[1].equals("-t") && args[3].equals("-w")) { 

            parseType(args[2]); 
            if (type != null) { 
                file = Path.of(args[4]); 
                write = true; 
            } 
        } 
    }
    
    private void parseType(String s) { 
        type = switch (s) {
            case "commit" -> Type.COMMIT;
            case "tree" -> Type.TREE;
            case "blob" -> Type.BLOB;
            case "tag" -> Type.TAG;
            default -> 
                throw new IllegalArgumentException("Unknow object type: " + s);
        };
    }

    public Type getType() {
        return type;
    }

    public boolean getWrite() {
        return write;
    }

    public Path getFile() {
        return file;
    }
}
