package temp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @title: Test
 * @Author younger
 * @Date: 2020/8/1 22:26
 * @Version 1.0
 */
@Slf4j
public class Test {

    private static final String ROOT = "ROOT";

    private static List<B> data;

    @org.junit.jupiter.api.Test
    public void test() {
        //<节点路径,id>,节点路径从一级节点开始，root的路径不存入
        Map<String, String> map = new HashMap<>(16);
        //获取root
        B root = getBById("1");
        map.put(ROOT, root.getId());
        //获取root的子树
        getData(root.getId(), "", map);
        //获取导入数据
        List<A> anImport = getImport();
        //存储待插入的实体
        List<B> importModels = new ArrayList<>();

        anImport.forEach(a -> {
            List<String> colValues = a.getColValues();
            for (int i = 0; i < colValues.size(); i++) {
                //自己的路径
                String str = String.join(",", colValues.subList(0, i + 1));
                //节点存在
                if (!Objects.isNull(map.get(str))) {
                    //末尾节点
                    if (i == colValues.size() - 1) {
                        //执行更新操作或抛出异常
                        throw new RuntimeException("该节点已经存在");
                    }
                    continue;
                }

                //第一层的父路径是root
                String parentStr = i == 0 ? ROOT : String.join(",", colValues.subList(0, i));
                //生成新节点id
                String id = UUID.randomUUID().toString();
                //非叶子节点，加入map集合
                if (i < colValues.size() - 1) {
                    map.put(str, id);
                }
                //创建新节点
                importModels.add(B.builder().remark((i + 1) + "").
                        name(colValues.get(i)).id(id).parentId(map.get(parentStr)).build());
            }
        });

        importModels.forEach(i -> System.out.println(i));
    }

    static List<A> getImport() {
        List<A> list = new ArrayList<>();
        list.add(A.builder().colValues(Arrays.asList(new String[]{"other2", "other10", "other11"})).name("test").build());
        list.add(A.builder().colValues(Arrays.asList(new String[]{"other2", "other5", "other7", "other8"})).name("test").build());
        list.add(A.builder().colValues(Arrays.asList(new String[]{"other3"})).name("test").build());
        return list;
    }

    static void getData(final String parentId, final String parentStr, final Map<String, String> map) {
        List<B> sonList = getSon(parentId);
        if (Objects.isNull(sonList) || sonList.isEmpty()) {
            return;
        }
        sonList.forEach(i -> {
            String path = i.getName();
            if (!Objects.isNull(parentStr) && !"".equals(parentStr)) {
                path = parentStr + "," + i.getName();
            }
            map.put(path, i.getId());
            getData(i.getId(), path, map);
        });
    }

    static List<B> getSon(String parentId) {
        return parentId == null ? null : data.stream().filter(i -> parentId.equals(i.getParentId())).collect(Collectors.toList());
    }

    static B getBById(String id) {
        return id == null ? null : data.stream().filter(i -> id.equals(i.getId())).collect(Collectors.toList()).get(0);
    }

    static {
        data = new ArrayList<>();
        data.add(B.builder().id("1").parentId(null).name("other1").remark("a,b,c").build());
//        data.add(B.builder().id("2").parentId("1").name("other2").remark("1").build());
//        data.add(B.builder().id("3").parentId("1").name("other3").remark("1").build());
//        data.add(B.builder().id("4").parentId("2").name("other4").remark("2").build());
//        data.add(B.builder().id("5").parentId("2").name("other5").remark("2").build());
//        data.add(B.builder().id("6").parentId("4").name("other6").remark("3").build());
    }
    /**
     *     1
     *   2  3
     *  4 5
     * 6
     *
     */
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class A {
    private List<String> colValues;
    private String name;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class B {
    private String id;
    private String parentId;
    private String remark;
    private String name;
}