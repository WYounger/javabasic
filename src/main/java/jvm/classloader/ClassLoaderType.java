package jvm.classloader;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @title: ClassLoaderType
 * @Author younger
 * @Date: 2020/7/29 23:04
 * @Version 1.0
 */

@Slf4j
public class ClassLoaderType {

    @Test
    public void test() throws ClassNotFoundException {

        //BootstrapClassLoader:  null
        log.info("String : {}",String.class.getClassLoader());

        //PlatformClassLoader
        log.info("java.sql.Driver: {}",Class.forName("java.sql.Driver").getClassLoader());

        //AppClassLoader
        log.info("ClassLoaderType: {}",ClassLoaderType.class.getClassLoader());
    }
}
