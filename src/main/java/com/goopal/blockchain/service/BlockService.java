package com.goopal.blockchain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.goopal.blockchain.dao.BlockRepository;
import com.goopal.blockchain.dto.Block;

@Service
public class BlockService 
{
	@Autowired
	private BlockRepository blockRepository;
	
	public void save(JSONObject blockJson, long blockNum, String signee, long amount, long fee, long transCount)
	{
		Block block = new Block();
		block.setBlockNum(blockNum);
		block.setPreviousSecret(blockJson.getString("previous_secret"));
		block.setDelegateSignature(blockJson.getString("delegate_signature"));
		block.setUserTransactionIds(blockJson.getString("user_transaction_ids"));
		block.setId(blockJson.getString("id"));
		block.setBlockSize(blockJson.getIntValue("block_size"));
		block.setLatency(blockJson.getIntValue("latency"));
		block.setSigneeSharesIssued(blockJson.getString("signee_shares_issued"));
		block.setSigneeFeesCollected(blockJson.getIntValue("signee_fees_collected"));
		block.setSigneeFeesDestroyed(blockJson.getIntValue("signee_fees_destroyed"));
		block.setRandomSeed(blockJson.getString("random_seed"));
		block.setTimestamp(blockJson.getString("timestamp"));
		block.setSignee(signee);
		block.setAmount(amount);
		block.setFee(fee);
		block.setTransCount(transCount);
		
		blockRepository.saveAndFlush(block);
	}
	
	public boolean checkExist(long blockId)
	{
		return null != blockRepository.findOne(blockId);
	}
	
	public long getMaxBlockNum()
	{
		return blockRepository.getMaxBlockNum();
	}
}
