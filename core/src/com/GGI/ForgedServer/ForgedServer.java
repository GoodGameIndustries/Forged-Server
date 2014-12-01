package com.GGI.ForgedServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;

public class ForgedServer extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	public boolean timeout=true;
	public ArrayList<Connection> clients = new ArrayList<Connection>();
	public ServerSocketHints hints = new ServerSocketHints();
	public ServerSocket rServer;
	public ServerSocket sServer;
	
	@Override
	public void create() {
		rServer = Gdx.net.newServerSocket(Protocol.TCP, 4441, hints);
		sServer = Gdx.net.newServerSocket(Protocol.TCP, 4442, hints);
		
		
		
		// setup a server thread where we wait for incoming connections
		// to the server
		new Thread(new Connector(this)).start();
			//end listen for new connections
			
			//listen and respond to clients
		//new Thread(new Brodcast(this)).start();
			//end listen and respond to clients
			
		}
		
		
		
	}
