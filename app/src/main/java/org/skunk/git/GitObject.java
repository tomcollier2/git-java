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

    protected final byte[] data;

    protected GitObject(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

    protected static byte[] buildObject(ObjectType type, byte[] contents) { 
        
        String header = type + " " + contents.length + "\0"; 
    
        byte[] headerBytes = header.getBytes(); 
        byte[] object = new byte[headerBytes.length + contents.length]; 
    
        System.arraycopy(headerBytes, 0, object, 0, headerBytes.length); 
        System.arraycopy(contents, 0, object, headerBytes.length, contents.length); 
    
        return object; 
    
    }
    
}
