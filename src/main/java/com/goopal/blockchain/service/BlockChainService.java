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
		//获取最新的区块号
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
		//校验是否已处理 如已处理则不再重复处理
		if (blockService.checkExist(blockNum))
		{
			return;
		}
				
		//获取区块信息
		String blockString = blockChainSocketService.getBlock(blockNum);
		
		//如果未获取到区块信息 暂不处理
		if (null == blockString)
		{
			return;
		}
		
		JSONObject blockJson = JSONObject.parseObject(blockString);
		
		blockChainSocketService.getBlockTransactions(blockNum);
		
		
		//获取代理信息
		String signee = blockChainSocketService.getBlockSignee(blockNum);
		
		//获取交易编号列表
		JSONArray userTransactionIds = blockJson.getJSONArray("user_transaction_ids");
		
		//区块处理时间
		String timestamp = blockJson.getString("timestamp");
		
		long amount = 0;
		long fee = 0;
		long transCount = userTransactionIds.size();
		
		
		//逐条获取交易详情
		for (int i = 0; i < userTransactionIds.size(); i++)
		{
			String userTransactionId = userTransactionIds.getString(i);
			
			String transactionString = blockChainSocketService.getPrettyTransaction(userTransactionId);
			JSONObject transactionJson = JSONObject.parseObject(transactionString);
			amount = amount + transactionJson.getJSONArray("ledger_entries").getJSONObject(0).getJSONObject("amount").getLongValue("amount");
			fee = fee + transactionJson.getJSONObject("fee").getLongValue("amount");
			
			transactionService.save(transactionJson, signee, timestamp);
		}
		
		//区块信息入库
		blockService.save(blockJson, blockNum, signee, amount, fee, transCount);
		
		//统计信息更新
		statisService.updateStatis(transCount, amount, fee);
	}
}
