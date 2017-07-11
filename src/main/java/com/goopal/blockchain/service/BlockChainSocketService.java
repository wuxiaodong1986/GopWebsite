package com.goopal.blockchain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlockChainSocketService 
{
	@Autowired
	private SocketClientService socketClientService;
	
	/**
	 * 查询当前总共有多少块
	 */
	public long getBlockCount()
	{
		List<Object> params = new ArrayList<Object>();
		String result = socketClientService.send("blockchain_get_block_count", params);
		
		return Long.parseLong(result);
	}
	
	/**
	 * 借由区块数或ID 来指定区块并获取其区块头 (header)信息
	 */
	public String getBlock(long blockNum)
	{
		List<Object> params = new ArrayList<Object>();
		params.add(String.valueOf(blockNum));
		String result = socketClientService.send("blockchain_get_block", params);
		
		return result;
	}
	
	/**
	 * 根据区块号获取代理信息
	 */
	public String getBlockSignee(long blockNum)
	{
		List<Object> params = new ArrayList<Object>();
		params.add(String.valueOf(blockNum));
		String result = socketClientService.send("blockchain_get_block_signee", params);
		
		return result;
	}
	
	/**
	 * 根据区块号获取指定区块的全部详细交易信息
	 */
	public String getBlockTransactions(long blockNum)
	{
		List<Object> params = new ArrayList<Object>();
		params.add(String.valueOf(blockNum));
		String result = socketClientService.send("blockchain_get_block_transactions", params);
		
		return result;
	}
	
	/**
	 * 获取交易号获取详细交易信息
	 */
	public String getPrettyTransaction(String userTransactionId)
	{
		List<Object> params = new ArrayList<Object>();
		params.add(userTransactionId);
		String result = socketClientService.send("blockchain_get_pretty_transaction", params);
		
		return result;
	}
}
