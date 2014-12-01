package com.GGI.ForgedServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.badlogic.gdx.net.Socket;

public class Connector implements Runnable{

	public ForgedServer s;
	
	public Connector(ForgedServer s){
		this.s=s;
	}
	
	@Override
	public void run() {
		while(true){
			//listen for new connections
			try{
				Socket sClient = s.sServer.accept(null);
				Socket rClient = s.rServer.accept(null);
					try {
						String message = new BufferedReader(new InputStreamReader(rClient.getInputStream())).readLine();
						System.out.println(message);
						sClient.getOutputStream().write("Connected\n".getBytes());
						
						s.clients.add(0,new Connection(rClient,sClient));
						Sender se = new Sender(s,s.clients.get(0));
						
						new Thread(new Reader(s,s.clients.get(0),se)).start();
						
					} catch (IOException e) {
						System.out.println("an error occured");
					}
				}
				catch(Exception e){
					System.out.println("Timeout");
					continue;
				}
		}
		
	}

}
