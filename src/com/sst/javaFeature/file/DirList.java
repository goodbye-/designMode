package com.sst.javaFeature.file;

import java.util.regex.*;
import java.io.*;
import java.util.*;

public class DirList {
    public static void main(String[] args) {
        File path = new File(".");
        String[] list;
        if (args.length == 0)
            list = path.list();
        else
            list = path.list(new DirFilter(args[0]));
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String dirItem : list)
            System.out.println(dirItem);
    }
}

/**
 * @author shui
 *
 */
class DirFilter implements FilenameFilter {
    private Pattern pattern;

    public DirFilter(String regex) {
        pattern = Pattern.compile(regex);
    }

    /** 
    * @author : sst HJ
    * @date 创建时间：2018年1月15日 下午5:37:48 
    * @version 1.0 
    * @parameter  
    * @since  
    * @return  
    * @方法说明：你tm的不加override吗？
    * 
    */
    @Override
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
} /*
   * Output: DirectoryDemo.java DirList.java DirList2.java DirList3.java
   */// :~
