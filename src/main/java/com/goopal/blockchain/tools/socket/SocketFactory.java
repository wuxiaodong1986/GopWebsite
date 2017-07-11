package com.goopal.blockchain.tools.socket;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class SocketFactory 
{
	@Value("${goopal.socket.host}")
	private String host;
	
	@Value("${goopal.socket.port}")
	private int port;
	
	@Value("${goopal.socket.timeout}")
	private int timeout;
	
	public synchronized Socket getInstance()
	{
		Socket socket = null;
		try 
		{
			socket = new Socket();
			
			SocketAddress remoteAddr = new InetSocketAddress(host,port);
			
			socket.connect(remoteAddr, timeout);
		}
		catch (Throwable e) 
		{
			e.printStackTrace();
		}
		
		return socket;
	}
}
