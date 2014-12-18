package com.GGI.ForgedServer;

import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;

public class ForgedServer extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	public boolean timeout=true;
	public ArrayList<Connection> clients = new ArrayList<Connection>();
	public ServerSocketHints hints = new ServerSocketHints();
	public ServerSocket rServer;
	public ServerSocket sServer;
	public Socket sClient;
	public Socket rClient;
	public Texture bg;
	public SpriteBatch pic;
	public String title="Follow us on twitter @GoodGameInd",mOTD="Get the latest news on updates for Forged! Get special free items and keep up to date on the best MMMO ever!";
	@Override
	public void create() {
		bg = new Texture(Gdx.files.internal("ForgedServer.png"));

		 pic = new SpriteBatch();
		rServer = Gdx.net.newServerSocket(Protocol.TCP, 4441, hints);
		sServer = Gdx.net.newServerSocket(Protocol.TCP, 4442, hints);
		
		SocketHints hints2 = new SocketHints();
		sClient = Gdx.net.newClientSocket(Protocol.TCP, "localhost", 4443, hints2);
		rClient = Gdx.net.newClientSocket(Protocol.TCP, "localhost", 4444, hints2);
		
		
		new Thread(new DBReader(this,rClient)).start();
		send("connect");
		// setup a server thread where we wait for incoming connections
		// to the server
		new Thread(new Connector(this)).start();
			//end listen for new connections
			
			//listen and respond to clients
		//new Thread(new Brodcast(this)).start();
			//end listen and respond to clients
			
		}
	
	public void render(){
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		pic.begin();
		pic.draw(bg,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		pic.end();
	}
		
	public void send(String s){
		try {
			s+="\n";
			sClient.getOutputStream().write(s.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	}
