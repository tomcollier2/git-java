package org.skunk.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;

public class Compression {

    // Byte array compressor
    public static byte[] compress(byte[] data) {

        try {

            ByteArrayOutputStream output = new ByteArrayOutputStream();

            DeflaterOutputStream compressor =
                    new DeflaterOutputStream(output);

            compressor.write(data);

            compressor.close();

            return output.toByteArray();

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

    }
    
}
