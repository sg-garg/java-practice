package com.sg.thread.helper;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by shara on 27-07-2017.
 */
public class ThreadPoolRejectHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println(r + ": Thread is rejected");
    }
}
