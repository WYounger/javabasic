package atomictest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Demo1 {
    private static final int LENGTH = 10;

    public static void main(String[] args) {
        List<A> sourceList = getData("ii");

        /**
         * 排序
         * 对于简单的比较大小，推荐使用Comparator.comparing(Class::getAttribute)
         * 复杂的比较，使用(o1, o2) -> {...}
         */
        List<A> sortedList1 = sourceList.stream().sorted((o1, o2) -> {
            return o1.getAge() - o2.getAge();
        }).collect(Collectors.toList());
        List<A> sortedList = sourceList.stream().sorted(Comparator.comparing(A::getAge)).collect(Collectors.toList());
        sortedList.forEach(System.out::println);

        /**
         * 取最大、最小元素
         */
        A max = sourceList.stream().max(Comparator.comparing(A::getAge)).get();
        A min = sourceList.stream().min(Comparator.comparing(A::getAge)).get();

        /**
         * 过滤元素
         */
        List<A> filterList = sourceList.stream().filter(a -> a.getAge() > 5).collect(Collectors.toList());
        filterList.forEach(System.out::println);

        /**
         * 使用distinct去重，依靠元素的equal和hashCode方法，
         * 所以必须重写equals和hashCode方法
         */
        List<A> distinctList = sourceList.stream().distinct().collect(Collectors.toList());
        distinctList.forEach(System.out::println);

        /**
         * 提取一个对象的部分属性
         */
        List<String> strings = sourceList.stream().map(o -> o.getName()).collect(Collectors.toList());
        List<String> string2 = sourceList.stream().map(A::getName).collect(Collectors.toList());


    }

    static List<A> getData(@Nonnull String tset) {
        ArrayList<A> list = new ArrayList<>(16);
        for (int i = LENGTH; i >= 1; i--) {
            list.add(A.builder().name("young" + i).age(i).build());
            list.add(A.builder().name("young" + i).age(i).build());
        }
        return list;
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class A {
    private String name;
    private Integer age;

    @Override
    public boolean equals(Object o) {
        return this == o ? true : (!Objects.isNull(o)
                && (o instanceof A)
                && (((A) o).getName().equalsIgnoreCase(this.name))
        );
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
