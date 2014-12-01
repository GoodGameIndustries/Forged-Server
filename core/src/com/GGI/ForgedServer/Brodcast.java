/**
 * 
 */
package com.GGI.ForgedServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.badlogic.gdx.net.Socket;

/**
 * @author Emmett Deen
 *
 */
public class Brodcast implements Runnable{

	public ForgedServer s;
	
	public Brodcast(ForgedServer s){
		this.s=s;
	}
	
	@Override
	public void run() {
		while(true){
			//System.out.println(s.clients.size());
		for(int i = 0; i < s.clients.size(); i++){
			Socket client = s.clients.get(i).s;
			try {
				
				//if(message.equals("test\n")){
					client.getOutputStream().write("check\n".getBytes());
					System.out.println("S:Sent");
				//}
			} catch (IOException e) {
				System.out.println("an error occured");
				s.clients.remove(i);
			}
			
		}
		
	}
	}

}
