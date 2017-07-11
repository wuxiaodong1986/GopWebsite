package com.goopal.blockchain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.goopal.blockchain.dao.TransactionRepository;
import com.goopal.blockchain.dto.Transaction;

@Service
public class TransactionService 
{
	@Autowired
	private TransactionRepository transactionRepository;
	
	public void save(JSONObject transactionJson, String signee, String timestamp)
	{
		Transaction transaction = new Transaction();
		transaction.setTrxId(transactionJson.getString("trx_id"));
		transaction.setBlockNum(transactionJson.getLongValue("block_num"));
		transaction.setAddress1(transactionJson.getJSONArray("ledger_entries").getJSONObject(0).getString("from_account"));
		transaction.setAddress2(transactionJson.getJSONArray("ledger_entries").getJSONObject(0).getString("to_account"));
		transaction.setAmount(transactionJson.getJSONArray("ledger_entries").getJSONObject(0).getJSONObject("amount").getLongValue("amount"));
		transaction.setFee(transactionJson.getJSONObject("fee").getLongValue("amount"));
		transaction.setTimestamp(timestamp);
		transaction.setMemo(transactionJson.getJSONArray("ledger_entries").getJSONObject(0).getString("memo"));
		transaction.setSignee(signee);
		
		transactionRepository.saveAndFlush(transaction);
	}
}
