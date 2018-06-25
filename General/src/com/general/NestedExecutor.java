package com.general;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.omg.SendingContext.RunTime;

public class NestedExecutor {
	
	public static void main(String[] args) {
		HeavyWorkRunnable h =new HeavyWorkRunnable();
		
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.submit(h);
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				executor.shutdown();
			}
		});
	}
}

class HeavyWorkRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Doing heavy processing - START "+Thread.currentThread().getName());
        try {
        	ExecutorService executor = Executors.newFixedThreadPool(5);
        	while(true){

        		System.gc();
        		System.runFinalization();
        		System.out.println("External thread");
        		for(int i=0;i<10;i++){
	        		HeavyWorkRunnableInner h =new HeavyWorkRunnableInner();
	        		
	        		executor.submit(h);
	        		Runtime.getRuntime().addShutdownHook(new Thread() {
	        			@Override
	        			public void run() {
	        				executor.shutdown();
	        			}
	        		});
        		}
        		Thread.sleep(1000);
        	}
            
            //Get database connection, delete unused data from DB
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Doing heavy processing - END "+Thread.currentThread().getName());
    }

}


class HeavyWorkRunnableInner implements Runnable {

    @Override
    public void run() {
        System.out.println("Doing heavy processing - START "+Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
            //Get database connection, delete unused data from DB
            doDBProcessing();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Doing heavy processing - END "+Thread.currentThread().getName());
    }

    private void doDBProcessing() throws InterruptedException {
        System.out.println("Inner thread..");
    }

}
