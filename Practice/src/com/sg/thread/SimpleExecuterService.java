package com.sg.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by shara on 27-07-2017.
 */

public class SimpleExecuterService {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            WorkerThread workerThread = new WorkerThread("worker-"+i);
            executorService.execute(workerThread);
        }
        executorService.shutdown();
        while (!executorService.isTerminated()){

        }
        System.out.println("Main thread is ended now.");
    }
}
