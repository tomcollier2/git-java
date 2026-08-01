package org.skunk.options;

import java.nio.file.Path;

public class HashObjectOptions {

    private enum Type { COMMIT, TREE, BLOB, TAG; };

    private boolean write = false; 
    private Path file = null; 
    private Type type = Type.BLOB; 
    
    public HashObjectOptions(String[] args) { 

        for (int i = 1; i < args.length; i++) {
            switch (args[i]) {
                case "-w":
                    write = true;
                    break;

                case "-t":
                    type = parseType(args[++i]);
                    break;

                default:
                    file = Path.of(args[i]);
            }
        } 
    }
    
    private Type parseType(String s) { 
        type = switch (s) {
            case "commit" -> Type.COMMIT;
            case "tree" -> Type.TREE;
            case "blob" -> Type.BLOB;
            case "tag" -> Type.TAG;
            default -> 
                throw new IllegalArgumentException("Unknow object type: " + s);
        };
        return type;
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
