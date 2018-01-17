package com.sst.javaFeature.file;

//: io/FormattedMemoryInput.java
import java.io.*;

public class FormattedMemoryInput {
    public static void main(String[] args) throws IOException {
        try {
            DataInputStream in = new DataInputStream(
                    new ByteArrayInputStream(BufferedInputFile.read("./src/com/sst/javaFeature/file/BufferedInputFile.java").getBytes()));
            while (true)
                System.out.print((char) in.readByte());
        } catch (EOFException e) {
            e.printStackTrace();//eofException
            System.err.println("End of stream");
        }
    }
} /* (Execute to see output) */// :~
