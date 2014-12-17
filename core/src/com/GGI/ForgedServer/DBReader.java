package com.GGI.ForgedServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.badlogic.gdx.net.Socket;

public class DBReader implements Runnable{

	public Socket r;
	public ForgedServer f;
	
	public DBReader(ForgedServer f, Socket r){
		this.f=f;
		this.r=r;
	}
	
	@Override
	public void run() {
		while(true){
			String message;
			try {
				message = new BufferedReader(new InputStreamReader(r.getInputStream())).readLine();
				System.out.println(message);
				
				//if statements go here
			} catch (IOException e) {
				System.out.println("error");
			}
			
		}
		
	}

}
