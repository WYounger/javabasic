package generic;

public class SetGet<T> {

    private T t;

    public SetGet(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        System.out.println(clazz);
        t = clazz.newInstance();
    }

    public T getT() {
        return t;
    }

    public static void main(String[] args) {
        System.out.println(Integer.class);
    }
}


class  MyString{
    public String data = "data";
}