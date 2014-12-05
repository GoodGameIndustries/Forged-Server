package com.GGI.ForgedServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reader implements Runnable{

	public ForgedServer s;
	public Connection c;
	public Sender se;
	
	public Reader(ForgedServer s,Connection c,Sender se){
		this.s=s;
		this.c=c;
		this.se=se;
	}

	@Override
	public void run() {
		while(true){
			String message;
			try {
				message = new BufferedReader(new InputStreamReader(c.r.getInputStream())).readLine();
				System.out.println(message);
				if(se.send("Received\n")){
					break;
				}
			} catch (IOException e) {
				
			}
			
		}
		//return;
		
	}
	
}
