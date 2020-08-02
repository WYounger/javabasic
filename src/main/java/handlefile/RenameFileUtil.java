package handlefile;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Objects;

/**
 * @title: RenameFileUtil
 * @Author younger
 * @Date: 2020/7/18 18:44
 * @Version 1.0
 */
public class RenameFileUtil {

    /**
     * 修改文件名
     *
     * @param basePath    基础文件夹路径
     * @param reg         待匹配的文件名称正则表达式
     * @param replacement 代替的文字
     */
    public static void renameFile(final String basePath, final String reg, final String replacement) {

        Objects.requireNonNull(basePath);
        Objects.requireNonNull(reg);
        Objects.requireNonNull(replacement);

        File baseFile = new File(basePath);

        FileHandler handler = file -> {
            String fileName = file.getName();
            String newFileName = fileName.replaceAll(reg, replacement);
            if (!fileName.equals(newFileName)) {
                file.renameTo(new File(file.getParent() + File.separator + newFileName));
            }

        };

        HandleFile.traverseFiles(baseFile, handler);
    }

    @Test
    public void test(){
//        renameFile("E:\\courese\\剑指Java面试-Offer直通车\\第1章 课程导学","_慕课网","");
        renameFile("E:\\courese\\剑指Java面试-Offer直通车","_慕课网","");
    }
}


