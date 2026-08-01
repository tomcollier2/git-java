package org.skunk.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.InflaterInputStream;

public class Decompression {

    // Byte array decompressor
    public static byte[] decompress(byte[] data) {

        try {

            ByteArrayInputStream input =
                    new ByteArrayInputStream(data);

            InflaterInputStream inflater =
                    new InflaterInputStream(input);

            ByteArrayOutputStream output =
                    new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];

            int bytesRead;

            while ((bytesRead = inflater.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }

            return output.toByteArray();

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

    }
    
}
