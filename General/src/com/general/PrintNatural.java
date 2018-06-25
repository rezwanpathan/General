package com.general;

class PrintOdd implements Runnable {
    int count = 1;
    private Object common;

    PrintOdd(Object common) {
        this.common = common;
    }

    @Override
    public void run() {
        synchronized (common) {
            while (count <=10) {
                try {
                    common.notify();
                    System.out.println(count);
                    count=count+2;
                    common.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}

class PrintEven implements Runnable {

    int count = 2;
    private Object common;

    PrintEven(Object common) {
        this.common = common;
    }

    @Override
    public void run() {
        synchronized (common) {
            while (count <=10) {
                try {
                    common.notify();
                    System.out.println(count);
                    count=count+2;
                    common.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}

public class PrintNatural {

	
    public static void main(String args[]) throws Exception{
    	Object obj = new Object();
        Runnable r = new PrintOdd(obj);
        Thread printOdd = new Thread(r);

        Runnable r2 = new PrintEven(obj);
        Thread printEven = new Thread(r2);

        printOdd.start();   
        Thread.sleep(1);
        printEven.start();

    }

}