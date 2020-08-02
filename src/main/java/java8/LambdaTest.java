package java8;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LambdaTest {

    static List<Dish> menu;

    @BeforeAll
    public static void beofre() {
        menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
    }

    @Test
    public void test() {
        Entity e1 = new Entity("1");
        Entity e2 = new Entity("2");
        Entity e3 = new Entity("3");
        List<Entity> list = new ArrayList<>();
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.forEach(Entity::fc);
    }

    @Test
    public void test2() {

    }

    @Test
    public void test3() {
        List<String> collect = menu.stream()
                .map(Dish::getName).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    @Test
    public void testReduce() {
        List<Integer> list = Arrays.asList(null);
    }

    public static <E> List<E> makeList(E... elements) {
        List<E> list = new ArrayList<>(elements.length);
        List<E> es = Arrays.asList(elements);
        es.forEach(list::add);

        return list;
    }
}


class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public boolean isCaloriesOver500() {
        return this.calories > 500;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return name + ": " + calories;
    }

    public enum Type {MEAT, FISH, OTHER}
}


class Entity {
    private String value;

    public Entity(String value) {
        this.value = value;
    }

    public void fc() {
        System.out.println(value);
    }
}