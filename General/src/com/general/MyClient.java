package com.general;
import java.io.*;  
import java.net.*;  
public class MyClient {

	  public static void main(String args[]) throws Exception {
	    Socket socket1;
	    int portNumber = 1777;
	    String str = "initialize";

	    socket1 = new Socket(InetAddress.getLocalHost(), portNumber);


	    PrintWriter pw = new PrintWriter(socket1.getOutputStream(), true);

	    pw.println(str);
	    pw.println(".hello");
	    pw.println("bye");

	   	pw.close();
	    socket1.close();
	  }
}  