package com.goopal.blockchain.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.goopal.blockchain.tools.BlockchainTool;
import com.goopal.blockchain.tools.exception.GoopalException;
import com.goopal.blockchain.tools.socket.SocketFactory;

@Service
public class SocketClientService 
{
	@Autowired
	private SocketFactory socketFactory;
	
	@Value("${goopal.socket.username}")
	private String username;
	
	@Value("${goopal.socket.password}")
	private String password;
	
	public String send(String method, List<Object> params)
	{
		long idSend = BlockchainTool.getId();
		
		JSONObject sendObject = new JSONObject();
		sendObject.put("jsonrpc", "2.0");
		sendObject.put("id", idSend);
		sendObject.put("method", method);
		
		JSONArray paramsObject = new JSONArray();
		paramsObject.addAll(params);
		
		sendObject.put("params", paramsObject);
		
		String sendMessage = sendObject.toJSONString();
		
		String result = null;
		
		PrintWriter os = null;
		
		BufferedReader is = null;
		
		Socket socket = null;
		try 
		{
			//获取链接
			socket = socketFactory.getInstance();
			os = new PrintWriter(socket.getOutputStream());
			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			//登录
			login(os, is);
			
			//发送报文
			os.println(sendMessage);
			os.flush();
			
			//获取报文
			String returnMessage = is.readLine();
			JSONObject returnObject = JSONObject.parseObject(returnMessage);
			long idReturn = returnObject.getLongValue("id");
			
			if (idSend != idReturn)
			{
				throw new GoopalException("发送id和返回id不一致 " + returnMessage);
			}
			
			result = returnObject.getString("result");
		
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				is.close();
			} 
			catch (Throwable e) 
			{
				e.printStackTrace();
			}
			
			try 
			{
				os.close();
			} 
			catch (Throwable e) 
			{
				e.printStackTrace();
			}
			
			try 
			{
				socket.close();
			} 
			catch (Throwable e) 
			{
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	private void login(PrintWriter os, BufferedReader is) throws IOException
	{
		long id = BlockchainTool.getId();
		
		JSONObject sendObject = new JSONObject();
		sendObject.put("jsonrpc", "2.0");
		sendObject.put("id", id);
		sendObject.put("method", "login");
		
		JSONArray paramsObject = new JSONArray();
		paramsObject.add(username);
		paramsObject.add(password);
		
		sendObject.put("params", paramsObject);
		
		String sendMessage = sendObject.toJSONString();
		
		os.println(sendMessage);
		os.flush();
		
		String returnMessage = is.readLine();
		
		JSONObject returnObject = JSONObject.parseObject(returnMessage);
		
		String result = returnObject.getString("result");
		
		if (!"true".equals(result))
		{
			throw new GoopalException("登录失败 " + returnMessage);
		}
	}
}
