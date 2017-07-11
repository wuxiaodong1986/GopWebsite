package com.goopal.blockchain.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SocketClientServiceTest {

	@Autowired
	private SocketClientService socketClientService;
	
	@Test
	public void test() 
	{
		List<Object> params = new ArrayList<Object>();
		params.add("a");
		params.add("b");
		String returnmessage = socketClientService.send("login", params);
		System.out.println(returnmessage);
	}

}
