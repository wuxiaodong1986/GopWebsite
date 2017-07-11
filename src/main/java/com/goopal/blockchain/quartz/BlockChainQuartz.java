package com.goopal.blockchain.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.goopal.blockchain.service.BlockChainService;
import com.goopal.blockchain.service.StatisService;

@Service
public class BlockChainQuartz 
{
	@Autowired
	private BlockChainService blockChainService;
	
	@Autowired
	private StatisService statisService;
	
	/**
	 * 定时任务
	 * 从socket获取区块数据 入库 并更新统计表
	 * 间隔5秒
	 */
	@Scheduled(fixedRate=5000)
	public void getChainQuartzFormSocket()
	{
		//获取已处理的最大的区块号并加1
		long blockNum = blockChainService.getLastBlockNum() + 1;
		
//		long blockNum = blockChainService.getNewBlockNum();
		
		//获取更新的一个区块信息并入库
		blockChainService.blockChainToDb(blockNum);
	}
	
	/**
	 * 定时任务
	 * 更新统计表
	 * 每小时的第10分钟执行
	 * 统计前一小时的交易情况
	 */
	@Scheduled(cron = "0 10 * * * ?")
//	@Scheduled(fixedRate=5000)
	public void updateStatis()
	{
		statisService.updateHourStatis();
	}
}
