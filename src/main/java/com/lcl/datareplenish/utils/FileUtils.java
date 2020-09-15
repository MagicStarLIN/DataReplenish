package com.lcl.datareplenish.utils;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: FileUtils
 * @date 2020/1/8 10:57 上午
 */
public class FileUtils {
    private static String txtfilePath = "/Users/liuchanglin/Magic/update.txt";

    /**
     * @return java.util.List<java.lang.Long>
     * @Title readContentIdsFromFile
     * @Description 从文件中读取
     * @Author liuchanglin
     * @Date 2019/12/10 2:54 下午
     * @Param [path]
     **/
    public static List<String> readFromFile(String path, String codeSet) {
        System.err.println("--------------------从" + path + "读取ids--------------------");
        List<String> list = null;
        try {
            list = IOUtils.readLines(
                    new FileInputStream(new File(path)), codeSet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
