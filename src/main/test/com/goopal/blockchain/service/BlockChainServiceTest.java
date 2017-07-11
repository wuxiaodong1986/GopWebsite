package com.goopal.blockchain.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlockChainServiceTest 
{
	@Autowired
	private BlockChainService blockChainService;
	
	@Test
	public void test() 
	{
		long blockNum = blockChainService.getLastBlockNum();
		
//		blockNum=680235;
		
		blockChainService.blockChainToDb(blockNum+1);
	}

}
