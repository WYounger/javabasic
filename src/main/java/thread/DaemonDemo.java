package thread;

public class DaemonDemo {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 守护线程：为用户线程和JVM提供服务
         *  当用户线程终止后，守护线程也会退出
         */

        Thread thread = new Thread(()->{
            Long i = 0L;
            while (true){
                 i++;
                System.out.println("daemon thread !" + i);
            }
        });
        //设置为守护线程。必须在线程启动之前设置才有效
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(1 * 1000);
        System.out.println("main thread------------------------------------------------------!");
    }
}

