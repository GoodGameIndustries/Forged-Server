package com.GGI.ForgedServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

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
		System.out.println("Reader Started");
		while(true){
			String message;
			try {
				
				message = new BufferedReader(new InputStreamReader(c.r.getInputStream())).readLine();
				if(se.send(" ")){break;}
				System.out.println("Reading");
				if(message!=null){
				System.out.println(message);
				String[] breakDown = new String[0];
				try{
				breakDown = message.split(":");
				}
				catch(ArrayIndexOutOfBoundsException e){
					if(se.send("Invalid")){break;}
				}
				if(breakDown.length>2){
				
				if(breakDown[0].equals("login")){
					if(Gdx.files.local("/Players/"+breakDown[1]+"/account.txt").exists()){
					FileHandle file = Gdx.files.local("/Players/"+breakDown[1]+"/account.txt");
					String[] text = file.readString().split(":");
					if(breakDown[1].equals(text[2])&&breakDown[2].equals(text[3])){
						if(se.send("Online:"+s.title+":"+s.mOTD)){break;}
					}
					else{
						if(se.send("Invalid")){break;}
					}
					}
					else{
						if(se.send("Invalid")){break;}
					}
					
				}
				
				else if(breakDown[0].equals("createAccount")){
					if(!Gdx.files.local("/Players/"+breakDown[2]+"/account.txt").exists()){
					FileHandle file = Gdx.files.local("/Players/"+breakDown[2]+"/account.txt");
					file.writeString("account:"+breakDown[1]+":"+breakDown[2]+":"+breakDown[3],false);
					if(se.send("accountCreated")){break;}
					}
					else{
						if(se.send("Error Creating")){break;}
					}
				}
				}
				else{
					if(se.send("Invalid")){break;}
				}
				}
				//send statement with error check:  if(se.send("Received")){break;}
			} catch (IOException e) {
				
			}
			
		}
		//return;
		
	}
	
}
