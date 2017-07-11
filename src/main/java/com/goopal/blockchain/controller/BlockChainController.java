package com.goopal.blockchain.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goopal.blockchain.dao.BlockRepository;
import com.goopal.blockchain.dao.StatisRepository;
import com.goopal.blockchain.dao.TransactionRepository;
import com.goopal.blockchain.dto.Block;
import com.goopal.blockchain.dto.Statis;
import com.goopal.blockchain.dto.Transaction;

@Controller
public class BlockChainController 
{
	@Autowired
	private StatisRepository statisRepository;
	
	@Autowired
	private BlockRepository blockRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@ResponseBody
	@RequestMapping(value = "/getStatis.do", method= RequestMethod.POST)
	public Map<String, String> getStatis()
	{
		List<Statis> statises = statisRepository.findAll();
		
		Map<String, String> map = new HashMap<String, String>();
		
		for (Statis statis : statises)
		{
			map.put(statis.getStatisKey(), statis.getStatisValue());
		}
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getBlocks.do", method= RequestMethod.POST)
	public List<Block> getBlocks()
	{
		PageRequest pageable = new PageRequest(0 ,6, new Sort(Direction.DESC, "blockNum"));
		
		Page<Block> page = blockRepository.findAll(pageable);
		
		return page.getContent();
	}
	
	@ResponseBody
	@RequestMapping(value = "/getTransactions.do", method= RequestMethod.POST)
	public List<Map<String, String>> getTransactions()
	{
		PageRequest pageable = new PageRequest(0, 6, new Sort(Direction.DESC, "blockNum"));
		
		Page<Transaction> page = transactionRepository.findAll(pageable);
		
		List<Transaction> transactions = page.getContent();
		
		List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		
		for (Transaction transaction : transactions)
		{
			Map<String, String> map = new HashMap<String, String>();
			
			String disTime = "";
			try 
			{
				String timestamp = transaction.getTimestamp();
				
				Date date = df.parse(timestamp);
				
				Date now = new Date();
				
				long diff = now.getTime() - date.getTime();
				
				long second = (diff/(1000))%60;
				long minutes = (diff/(1000*60))%60;
				long hour = (diff/(1000*60*60))%24;
				long day = diff/(1000*60*60*24);
				
				if (0 != second)
				{
					disTime = second + " second " + disTime;
				}
				if (0 != minutes)
				{
					disTime = minutes + " minutes " + disTime;
				}
				if (0 != hour)
				{
					disTime = hour + " hour " + disTime;
				}
				if (0 != day)
				{
					disTime = day + " day " + disTime;
				}
			} 
			catch (ParseException e) 
			{
				e.printStackTrace();
			}
			
			map.put("trxId", transaction.getTrxId());
			map.put("disTime", disTime);
			map.put("amount", String.valueOf(transaction.getAmount()));
			
			maps.add(map);
		}
		
		return maps;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getBlockByBlockNum.do", method= RequestMethod.POST)
	public Block getBlockByBlockNum(Long blockNum)
	{
		Block block = blockRepository.findOne(blockNum);
		
		Block preBlock = blockRepository.findOne(blockNum-1);
		
		block.setPreId(preBlock.getId());
		
		return block;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getTransByBlockNum.do", method= RequestMethod.POST)
	public List<Transaction> getTransByBlockNum(Long blockNum)
	{
		List<Transaction> transactions = transactionRepository.findByBlockNum(blockNum);
		
		return transactions;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getTransactionByTrxId.do", method= RequestMethod.POST)
	public Transaction getTransactionByTrxId(String trxId)
	{
		Transaction transaction = transactionRepository.findOne(trxId);
		
		return transaction;
	}
}
