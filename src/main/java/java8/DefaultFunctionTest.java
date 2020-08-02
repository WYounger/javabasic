package java8;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author Young
 */
public class DefaultFunctionTest {

    @Test
    public void test() {

//        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.
//                supplyAsync(() -> 1).
//                thenApplyAsync(i -> ++i).
//                thenCompose(i -> CompletableFuture.supplyAsync(() -> i + 1));
//
//        Integer join = integerCompletableFuture.join();l,;
//        System.out.println(join);

        List<Integer> list = new ArrayList<>();
        list.add(1);
        List<CompletableFuture<Integer>> collect = list.stream().
                map(i -> CompletableFuture.supplyAsync(() -> i + 1)).
                map(future -> future.thenApply(i -> i + 1)).map(future -> future.thenCompose(
                i -> CompletableFuture.supplyAsync(() -> i + 1)
        )).collect(Collectors.toList());
        Integer result = collect.get(0).join();
        System.out.println(result);
    }



    @Test
    public  void test1(){
        deleteFile(new File("C:\\Users\\Young\\Documents\\11289"));
    }

    static void deleteFile(File file){
        Objects.requireNonNull(file);
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(File t : files){
                if(t.isDirectory()){
                    deleteFile(t);
                }else{
                    if(isDelete(t.getName())){
                        t.delete();
                    }
                }
            }
        }else{
            if(isDelete(file.getName())){
                file.delete();
            }
        }
    }

    static boolean isDelete(String fileName){
        Objects.requireNonNull(fileName);
        if(fileName.endsWith("pdf") || fileName.endsWith("m4a")){
            return true;
        }
        return false;
    }


    @Test
    public void test2(){
        Integer[] arr = {1,2,3};
        List<Integer> integers = Arrays.asList(arr);
    }

    @Test
    public void test3(){
        Integer a = 1;
    }

}

class B implements A {

    @Override
    public void test() {
        System.out.println("test");
    }

    @Override
    public void echo() {
        System.out.println("new echo");
    }
}


@FunctionalInterface
interface A {

    void test();

    default void echo() {
        System.out.println("echo");
    }
}