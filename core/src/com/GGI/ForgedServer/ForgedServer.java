package com.GGI.ForgedServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;

public class ForgedServer extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create() {
		ServerSocketHints hints = new ServerSocketHints();
		final ServerSocket server = Gdx.net.newServerSocket(Protocol.TCP, 1997, hints);
		
		// setup a server thread where we wait for incoming connections
		// to the server
		while(true){
		new Thread(new Runnable() {
			@Override
			public void run() {
				// wait for the next client connection
				try{
				Socket client = server.accept(null);
					try {
						String message = new BufferedReader(new InputStreamReader(client.getInputStream())).readLine();
						Gdx.app.log("PingPongSocketExample", "got client message: " + message);
						client.getOutputStream().write("PONG\n".getBytes());
					} catch (IOException e) {
						Gdx.app.log("PingPongSocketExample", "an error occured", e);
					}
				}
				catch(Exception e){
					System.out.println("Timeout");
				}
				// read message and send it back
				
			}			
		}).start();
		}
		
		// create the client send a message, then wait for the 
		// server to reply
		
	}
}