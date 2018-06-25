package com.general;
class SBTable{  
	  
	 void printTable(int n){  
	   synchronized(this){//synchronized block  
	     for(int i=1;i<=5;i++){  
	      System.out.println(n*i);  
	      try{  
	       Thread.sleep(400);  
	      }catch(Exception e){System.out.println(e);}  
	     }  
	   }  
	 }//end of the method  
	}  
	  
	class SBMyThread1 extends Thread{  
		SBTable t;  
	SBMyThread1(SBTable t){  
	this.t=t;  
	}  
	public void run(){  
	t.printTable(5);  
	}  
	  
	}  
	class SBMyThread2 extends Thread{  
		SBTable t;  
	SBMyThread2(SBTable t){  
	this.t=t;  
	}  
	public void run(){  
	t.printTable(100);  
	}  
	}  
	  
	public class TestSynchronization3{  
		public static void main(String args[]){  
			SBTable obj = new SBTable();//only one object  
			SBMyThread1 t1=new SBMyThread1(obj);  
			SBMyThread2 t2=new SBMyThread2(obj);  
			t1.start();  
			t2.start();  
		}  
	}  