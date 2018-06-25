package com.general;
import java.io.*;  
import java.net.*;  
public class MyServer {

	  public static void main(String args[]) throws Exception {
	    int cTosPortNumber = 1777;
	    String str;

	    ServerSocket servSocket = new ServerSocket(cTosPortNumber);
	    System.out.println("Waiting for a connection on " + cTosPortNumber);

	    Socket fromClientSocket = servSocket.accept();

	    BufferedReader br = new BufferedReader(new InputStreamReader(fromClientSocket.getInputStream()));

	    while ((str = br.readLine()) != null) {
	      System.out.println("The message: " + str);

	      if (str.equals("bye")) {
	        break;
	      } 
	    }
	    br.close();

	    fromClientSocket.close();
	  }
}  