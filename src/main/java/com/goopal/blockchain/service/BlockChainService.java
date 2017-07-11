package com.goopal.blockchain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service
public class BlockChainService 
{
	@Autowired
	private BlockChainSocketService blockChainSocketService;
	
	@Autowired
	private BlockService blockService;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private StatisService statisService;
	
	
	public long getNewBlockNum()
	{
		//��ȡ���µ������
		long blockNum = blockChainSocketService.getBlockCount();
		
		return blockNum;
	}
	
	public long getLastBlockNum()
	{
		long blockNum = blockService.getMaxBlockNum();
		
		return blockNum;
	}
	
	@Transactional
	public void blockChainToDb(long blockNum)
	{
		//У���Ƿ��Ѵ��� ���Ѵ��������ظ�����
		if (blockService.checkExist(blockNum))
		{
			return;
		}
				
		//��ȡ������Ϣ
		String blockString = blockChainSocketService.getBlock(blockNum);
		
		//���δ��ȡ��������Ϣ �ݲ�����
		if (null == blockString)
		{
			return;
		}
		
		JSONObject blockJson = JSONObject.parseObject(blockString);
		
		blockChainSocketService.getBlockTransactions(blockNum);
		
		
		//��ȡ������Ϣ
		String signee = blockChainSocketService.getBlockSignee(blockNum);
		
		//��ȡ���ױ���б�
		JSONArray userTransactionIds = blockJson.getJSONArray("user_transaction_ids");
		
		//���鴦��ʱ��
		String timestamp = blockJson.getString("timestamp");
		
		long amount = 0;
		long fee = 0;
		long transCount = userTransactionIds.size();
		
		
		//������ȡ��������
		for (int i = 0; i < userTransactionIds.size(); i++)
		{
			String userTransactionId = userTransactionIds.getString(i);
			
			String transactionString = blockChainSocketService.getPrettyTransaction(userTransactionId);
			JSONObject transactionJson = JSONObject.parseObject(transactionString);
			amount = amount + transactionJson.getJSONArray("ledger_entries").getJSONObject(0).getJSONObject("amount").getLongValue("amount");
			fee = fee + transactionJson.getJSONObject("fee").getLongValue("amount");
			
			transactionService.save(transactionJson, signee, timestamp);
		}
		
		//������Ϣ���
		blockService.save(blockJson, blockNum, signee, amount, fee, transCount);
		
		//ͳ����Ϣ����
		statisService.updateStatis(transCount, amount, fee);
	}
}
