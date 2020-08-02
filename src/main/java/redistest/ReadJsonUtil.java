package redistest;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


//需要jar包:gson
class ReadJsonUtil {
    public static List<String> readJson(String path){
        //解析器
        JsonParser parser = new JsonParser();
        List<String>  names = new ArrayList<>();
        try {
            JsonObject object = (JsonObject) parser.parse(new FileReader(path));
            //取key对应value为jsonArray
            JsonArray array = object.get("pages").getAsJsonArray();
            //遍历array
            int size = array.size();
            for (int i = 0; i < size; i++) {
                JsonObject row = array.get(i).getAsJsonObject();
                names.add(row.get("part").getAsString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return names;
    }

    @Test
    public void test(){
        List<String> names = ReadJsonUtil.readJson("E:\\courese\\netty\\netty源码分析\\json.txt");
        int size = names.size();
        for(int i = 0;i<size;i++){
            String sourcePath = "E:\\courese\\netty\\netty源码分析\\" + "Java读源码之Netty深入刨析_哔哩哔哩 (゜-゜)つロ 干杯~-bilibili_"+(i+1) + ".flv";
            String destPath = "E:\\courese\\netty\\netty源码分析\\" + (names.get(i)) + ".flv";
            File source = new File(sourcePath);
            File dest = new File(destPath);
            source.renameTo(dest);
        }
    }
}

class ChangeName{
    public static void change(List<String> newNames,List<String> oldNames,String basePath){
        String eg = oldNames.get(0);
        String extension =  eg.substring(eg.lastIndexOf(".")+1);

        int len = newNames.size();
        for (int i = 0; i < len; i++) {
            String oldName = oldNames.get(i);
            String newName = newNames.get(i);
            File source = new File(basePath+ oldName);
            File dest = new File(basePath+newName+"."+extension);
            source.renameTo(dest);
        }
    }
}

class ChangeFileName{
    public static void changeFileName(String extension,int start,int end,String basePath,String json){

        String jsonFilePath = basePath + json;

        List<String> newNames = ReadJsonUtil.readJson(jsonFilePath);

        List<String> oldNames = new ArrayList<>();

        for (int i = start;i<=end;i++){
            oldNames.add(i+"."+extension);
        }
        ChangeName.change(newNames,oldNames,basePath);
    }
}