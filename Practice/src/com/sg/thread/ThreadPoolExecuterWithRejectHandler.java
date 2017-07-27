package com.sg.thread;

import com.sg.thread.helper.MyMonitorThread;
import com.sg.thread.helper.ThreadPoolRejectHandler;

import java.util.concurrent.*;

/**
 * Created by shara on 27-07-2017.
 */
public class ThreadPoolExecuterWithRejectHandler {
    public static void main(String[] args) {
        ArrayBlockingQueue<Runnable> blockingQueue= new ArrayBlockingQueue<Runnable>(2);
        ThreadPoolExecutor executor
                = new ThreadPoolExecutor( 2,4,10, TimeUnit.SECONDS,blockingQueue, Executors.defaultThreadFactory(), new ThreadPoolRejectHandler());
        MyMonitorThread monitor = new MyMonitorThread(executor, 5);
        Thread monitorThread = new Thread(monitor);
        monitorThread.start();

        for (int i = 0; i < 10; i++) {
            WorkerThread workerThread = new WorkerThread("cmd-"+i);
            executor.execute(workerThread);
        }

        executor.shutdown();

        while (executor.isTerminated()){

        }
        System.out.println("Finished Main Thread");




    }

}
