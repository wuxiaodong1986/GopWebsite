package com.goopal.blockchain.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlockChainSocketServiceTest 
{
	@Autowired
	private BlockChainSocketService blockChainSocketService;
	
	@Test
	public void test() 
	{
		long id = blockChainSocketService.getBlockCount();
		
		id=680235;
		
		System.out.println(id);
		
		String block = blockChainSocketService.getBlock(id);
		
		System.out.println(block);
		
		String blockTransactions = blockChainSocketService.getBlockTransactions(id);
		
		System.out.println(blockTransactions);
	}

}
