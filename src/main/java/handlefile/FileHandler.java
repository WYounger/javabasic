package handlefile;

import java.io.File;

/**
 * @title: FileHandler
 * @Author younger
 * @Date: 2020/7/18 18:49
 * @Version 1.0
 */

/**
 * 文件处理类
 */
@FunctionalInterface
public interface FileHandler {
    /**
     * 封装对文件的操作
     * @param file 目标文件
     */
    void handle(File file);
}
