package thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ThreadPoolTest {
}

interface ThreadPool{
    //执行一个任务
    void execute(Runnable job);
    //关闭线程池
    void shtudown();
    //添加工作线程
    void addWorkers(int num);
    //减少工作线程
    void removeWorker(int num);
    //得到正在登台执行的任务数量
    int getJobSize();
}

class DefaultThreadPool implements ThreadPool{
    //工作列表
    LinkedList<Runnable> jobs =  new LinkedList<>();
    //工作列表
    List<Worker> workers = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void execute(Runnable job) {
        if(job != null){
            synchronized (jobs){
                jobs.addLast(job);
                //仅仅唤醒一个worker线程进入同步队列
                jobs.notify();
            }
        }
    }


    @Override
    public void shtudown() {
        workers.forEach(Worker::shutdown);
    }

    @Override
    public void addWorkers(int num) {
        if(num > 0){
            synchronized(jobs){
                initializeWorkers(num);
            }
        }
    }

    @Override
    public void removeWorker(int num) {
        synchronized (jobs){//注意此处必须同步jobs。防止被移除的worker正在处理jobs
        }
    }

    @Override
    public int getJobSize() {
        return jobs.size();
    }

    private void initializeWorkers(int num){
        for(int i = 0; i < num; i++){
            Worker worker =  new Worker(jobs);
            workers.add(worker);
            //启动worker线程
            new Thread(worker).start();
        }
    }
}


//由一个线程取运行worker
class Worker implements Runnable{
    //是否工作
    private volatile boolean running = true;
    //任务列表
    private LinkedList<Runnable> jobs;

    public  Worker(LinkedList<Runnable> jobs){
        this.jobs = jobs;
    }

    @Override
    public void run() {
        while (running){
            //取一个任务
            Runnable job = null;
            synchronized (jobs){
                while (jobs.isEmpty()){
                    try {
                        jobs.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                job = jobs.removeFirst();
            }
            if(job != null){
                //执行任务
                job.run();
            }
        }
    }

    public void shutdown(){
        running = false;
    }
}