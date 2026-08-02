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

    public static GitObject parse(byte[] rawObject) {

        int index = 0;

        while (rawObject[index] != ' ') {
            index++;
        }

        String typeString = new String(rawObject, 0, index);
        int lengthStart = index + 1;

        index++;

        while (index < rawObject.length && rawObject[index] != 0) {
            index++;
        }

        if (index == rawObject.length) {
            throw new IllegalArgumentException(
                "Malformed Git object."
            );
        }

        String length = new String(rawObject, lengthStart, index - lengthStart);

        index++;

        byte[] contents = new byte[rawObject.length - index];

        System.arraycopy(rawObject, index, contents, 0, contents.length);

        int expectedSize = Integer.parseInt(length);

        if (expectedSize != contents.length) { 
            throw new IllegalArgumentException("Object size does not match header."); 
        }

        ObjectType type = ObjectType.valueOf(typeString.toUpperCase());

        return switch (type) {

            case BLOB -> Blob.fromBytes(contents);

            case TREE -> Tree.fromBytes(contents);

            default ->
                    throw new IllegalArgumentException(
                            "Unsupported object type"
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
