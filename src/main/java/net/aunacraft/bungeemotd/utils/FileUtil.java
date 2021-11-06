package net.aunacraft.bungeemotd.utils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;

public class FileUtil {

    /**
     * Get byte array from an InputStream most efficiently.
     * Taken from sun.misc.IOUtils
     * @param is InputStream
     * @param length Length of the buffer, -1 to read the whole stream
     * @param readAll Whether to read the whole stream
     * @return Desired byte array
     * @throws IOException If maximum capacity exceeded.
     */
    public static byte[] readFully(InputStream is, int length, boolean readAll)
            throws IOException {
        byte[] output = {};
        if (length == -1) length = Integer.MAX_VALUE;
        int pos = 0;
        while (pos < length) {
            int bytesToRead;
            if (pos >= output.length) {
                bytesToRead = Math.min(length - pos, output.length + 1024);
                if (output.length < pos + bytesToRead) {
                    output = Arrays.copyOf(output, pos + bytesToRead);
                }
            } else {
                bytesToRead = output.length - pos;
            }
            int cc = is.read(output, pos, bytesToRead);
            if (cc < 0) {
                if (readAll && length != Integer.MAX_VALUE) {
                    throw new EOFException("Detect premature EOF");
                } else {
                    if (output.length != pos) {
                        output = Arrays.copyOf(output, pos);
                    }
                    break;
                }
            }
            pos += cc;
        }
        return output;
    }

    /**
     * Read the full content of a file.
     * @param file The file to be read
     * @param emptyValue Empty value if no content has found
     * @return File content as string
     */
    public static String getFileContent(File file, String emptyValue) {
        if (file.isDirectory()) {
            System.err.println("Error while trying to read the given File. File is directory!");
            return emptyValue;
        }
        try {
            return new String(readFully(new FileInputStream(file), -1, true), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
            return emptyValue;
        }
    }

}
