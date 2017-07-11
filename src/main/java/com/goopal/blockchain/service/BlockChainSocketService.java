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
	 * ��ѯ��ǰ�ܹ��ж��ٿ�
	 */
	public long getBlockCount()
	{
		List<Object> params = new ArrayList<Object>();
		String result = socketClientService.send("blockchain_get_block_count", params);
		
		return Long.parseLong(result);
	}
	
	/**
	 * ������������ID ��ָ�����鲢��ȡ������ͷ (header)��Ϣ
	 */
	public String getBlock(long blockNum)
	{
		List<Object> params = new ArrayList<Object>();
		params.add(String.valueOf(blockNum));
		String result = socketClientService.send("blockchain_get_block", params);
		
		return result;
	}
	
	/**
	 * ��������Ż�ȡ������Ϣ
	 */
	public String getBlockSignee(long blockNum)
	{
		List<Object> params = new ArrayList<Object>();
		params.add(String.valueOf(blockNum));
		String result = socketClientService.send("blockchain_get_block_signee", params);
		
		return result;
	}
	
	/**
	 * ��������Ż�ȡָ�������ȫ����ϸ������Ϣ
	 */
	public String getBlockTransactions(long blockNum)
	{
		List<Object> params = new ArrayList<Object>();
		params.add(String.valueOf(blockNum));
		String result = socketClientService.send("blockchain_get_block_transactions", params);
		
		return result;
	}
	
	/**
	 * ��ȡ���׺Ż�ȡ��ϸ������Ϣ
	 */
	public String getPrettyTransaction(String userTransactionId)
	{
		List<Object> params = new ArrayList<Object>();
		params.add(userTransactionId);
		String result = socketClientService.send("blockchain_get_pretty_transaction", params);
		
		return result;
	}
}
