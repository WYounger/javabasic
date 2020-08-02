package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++){
            service.submit(new Entrance(i));
        }
        TimeUnit.SECONDS.sleep(5);
        Entrance.setCancel();
        service.shutdown();

        int sum = 0;
        for(Entrance entrance : Entrance.entrances){
            sum +=  entrance.getNumber();
        }
        System.out.println("sum: "+sum);
        System.out.println("total: "+Entrance.getTotal());
    }
}


class Count{

    //记录人数
    private int  count;

    public synchronized int increment(){
        //人数增加
        count++;
        return count;
    }

    public synchronized int get(){
        return count;
    }
}


class Entrance implements Runnable{
    //entrance标志
    private final int id;
    //一个entrance的记录数目
    private int number = 0;
    //所有入口的集合
    public static final List<Entrance> entrances = new ArrayList<>();
    //计数器
    private static Count count = new Count();
    //停止标志
    private static volatile boolean cancel = false;

    public Entrance(int id){
        this.id = id;
        entrances.add(this);
    }


    @Override
    public void run() {
        while (!cancel){
            synchronized (this){
                number++;
            }
            int total = count.increment();
            if(total < 2000)
                System.out.println(id+": "+number+","+total);
        }
    }

    //获取当前entrance的数量
    public synchronized int getNumber() {
        return number;
    }
    //获取总数量
    public static int getTotal(){
        return count.get();
    }

    public static void setCancel(){
        cancel = true;
    }
}