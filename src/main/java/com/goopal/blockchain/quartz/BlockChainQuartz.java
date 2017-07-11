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
	 * ��ʱ����
	 * ��socket��ȡ�������� ��� ������ͳ�Ʊ�
	 * ���5��
	 */
	@Scheduled(fixedRate=5000)
	public void getChainQuartzFormSocket()
	{
		//��ȡ�Ѵ������������Ų���1
		long blockNum = blockChainService.getLastBlockNum() + 1;
		
//		long blockNum = blockChainService.getNewBlockNum();
		
		//��ȡ���µ�һ��������Ϣ�����
		blockChainService.blockChainToDb(blockNum);
	}
	
	/**
	 * ��ʱ����
	 * ����ͳ�Ʊ�
	 * ÿСʱ�ĵ�10����ִ��
	 * ͳ��ǰһСʱ�Ľ������
	 */
	@Scheduled(cron = "0 10 * * * ?")
//	@Scheduled(fixedRate=5000)
	public void updateStatis()
	{
		statisService.updateHourStatis();
	}
}
