package com.sg.thread;

/**
 * Created by shara on 27-07-2017.
 */
public class StartWorkerThreads {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            WorkerThread workerThread = new WorkerThread("cmd-"+i);
            Thread t = new Thread(workerThread, "worker-"+i);
            t.start();
        }
    }
}
