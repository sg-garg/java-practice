package com.sg.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by shara on 27-07-2017.
 */
public class ScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(5);

        for (int i = 0; i < 10; i++) {
            WorkerThread workerThread = new WorkerThread("cmd-"+i);
            //scheduledExecutor.schedule(workerThread,3, TimeUnit.SECONDS);
            scheduledExecutor.scheduleAtFixedRate(workerThread, 0, 10, TimeUnit.SECONDS);
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        scheduledExecutor.shutdown();;
        while (!scheduledExecutor.isTerminated()){

        }
        System.out.println("Main Thread ended");
    }

}
