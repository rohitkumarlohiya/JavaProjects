package com.lohiya;
import java.util.concurrent.BlockingQueue;

public class PoolThread extends Thread {

    private BlockingQueue<Runnable> taskQueue = null;
    private boolean       isStopped = false;

    public PoolThread(BlockingQueue<Runnable> queue){
        taskQueue = queue;
    }

    public void run(){
        while(!isStopped()){
            try{
                Runnable runnable = (Runnable) taskQueue.take();
                runnable.run();
            } catch(Exception e){
                //log or otherwise report exception,
                //but keep pool thread alive.
            }
        }
    }

    public synchronized void doStop(){
        isStopped = true;
        this.interrupt(); //break pool thread out of dequeue() call.
    }

    public synchronized boolean isStopped(){
        return isStopped;
    }
}