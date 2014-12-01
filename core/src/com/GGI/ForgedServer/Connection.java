/**
 * 
 */
package com.GGI.ForgedServer;

import com.badlogic.gdx.net.Socket;

/**
 * @author Emmett Deen
 *
 */
public class Connection {

	public Socket r;
	public Socket s;
	public String name;
	
	
	public Connection(Socket r,Socket s){
		this.r=r;
		this.s=s;
	}
	
}
