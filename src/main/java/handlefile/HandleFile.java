package handlefile;

/**
 * @title: HandleFile
 * @Author younger
 * @Date: 2020/7/18 18:52
 * @Version 1.0
 */

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

/**
 * 分装遍历文件夹的算法
 */
public class HandleFile {

    /**
     * 遍历所有文件
     * @param file
     * @param handler
     */
    public static void traverseFiles(final File file,final FileHandler handler){
        Objects.requireNonNull(file);
        Objects.requireNonNull(handler);
        //遍历文件夹
        if(file.isDirectory()){
            File[] subFiles = file.listFiles();
            if(subFiles.length > 0){
                Arrays.stream(subFiles).forEach(i -> traverseFiles(i,handler));
            }
        }
        //最后处理自身
        handler.handle(file);
    }
}
