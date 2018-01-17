package com.sst.javaFeature.file;

import java.io.*;

public class BufferedInputFile {
    // Throw exceptions to console:
    /** 
    * @author : sst HJ
    * @date 创建时间：2018年1月16日 下午5:08:26 
    * @version 1.0 
    * @param filename
    * @return
    * @throws IOException 
    * @since   
    * @方法说明：如果换成inputStream的字节流，居然不知道怎么用!!
    */
    public static String read(String filename) throws IOException {
        // Reading input by lines:
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = in.readLine()) != null)
            sb.append(s + "\n");
        in.close();
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.print(read("./src/com/sst/javaFeature/file/BufferedInputFile.java"));
    }
} /* (Execute to see output) */// :~
