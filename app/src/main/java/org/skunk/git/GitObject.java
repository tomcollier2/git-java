package org.skunk.git;

public class GitObject {

    public enum ObjectType {
        BLOB,
        TREE,
        COMMIT,
        TAG;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    private final ObjectType type;
    private final byte[] contents;
    protected final byte[] data;

    protected GitObject(ObjectType type, byte[] contents) {
        this.type = type;
        this.contents = contents;
        this.data = buildObject(type, contents);
    }

    protected static byte[] buildObject(ObjectType type, byte[] contents) { 
        
        String header = type + " " + contents.length + "\0"; 
    
        byte[] headerBytes = header.getBytes(); 
        byte[] object = new byte[headerBytes.length + contents.length]; 
    
        System.arraycopy(headerBytes, 0, object, 0, headerBytes.length); 
        System.arraycopy(contents, 0, object, headerBytes.length, contents.length); 
    
        return object; 
    
    }

    public static GitObject parse(byte[] object) {

        int index = 0;

        while (object[index] != ' ') {
            index++;
        }

        String typeString = new String(object, 0, index);

        index++;

        while (object[index] != 0) {
            index++;
        }

        index++;

        byte[] contents = new byte[object.length - index];

        System.arraycopy(object, index, contents, 0, contents.length);

        return switch (typeString) {

            case "blob" -> Blob.fromBytes(contents);

            default ->
                    throw new IllegalArgumentException(
                            "Unsupported object type: " + typeString
                    );
        };
    }

    public byte[] getData() {
        return data;
    }

    public ObjectType getType() {
        return type;
    }

    public int getSize() {
        return contents.length;
    }

    public byte[] getContents() {
        return contents;
    }
    
}
