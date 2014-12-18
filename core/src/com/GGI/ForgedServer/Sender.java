package com.GGI.ForgedServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sender{

	public ForgedServer s;
	public Connection c;
	
	public Sender(ForgedServer s,Connection c){
		this.s=s;
		this.c=c;
		
	}

	public boolean send(String s) {
		try {
			s+="\n";
			System.out.println("Sending: " +s);
			c.s.getOutputStream().write(s.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.s.clients.remove(c);
			return true;
		}
		return false;
	}
	
}

