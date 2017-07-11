package com.goopal.blockchain.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goopal.blockchain.dao.StatisRepository;
import com.goopal.blockchain.dto.Statis;

@Service
public class StatisService 
{
	@Autowired
	private BlockService blockService;
	
	@Autowired
	private StatisRepository statisRepository;
	
	public void updateStatis(long transCount, long transAmount, long transFee)
	{
		List<Statis> statises =  statisRepository.getStatisForBlockTrans();
		
		for (Statis statis : statises)
		{
			if ("transCount".equals(statis.getStatisKey()))
			{
				transCount = Long.parseLong(statis.getStatisValue()) + transCount;
				statis.setStatisValue(String.valueOf(transCount));
			}
			else if ("transAmount".equals(statis.getStatisKey()))
			{
				transAmount = Long.parseLong(statis.getStatisValue()) + transAmount;
				statis.setStatisValue(String.valueOf(transAmount));
			}
			else if ("transFee".equals(statis.getStatisKey()))
			{
				transFee = Long.parseLong(statis.getStatisValue()) + transFee;
				statis.setStatisValue(String.valueOf(transFee));
			}
		}
		
		statisRepository.save(statises);
		statisRepository.flush();
	}
	
	/**
	 * ÿСʱͳ����Ϣ����
	 */
	public void updateHourStatis()
	{
		//��ȡ��һ��Сʱ��
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY,-1);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH");
		String time = df.format(cal.getTime());
		
		//��һСʱ�Ľ�����
		long hourCount = statisRepository.getCountByHour(time + "%");
		//���µ������
		long blockNum = blockService.getMaxBlockNum();
		//ϵͳ���е����ڵ�Сʱ��
		long hour = blockNum * 10 / 60 / 60;
		//ϵͳ���е����ڵĽ�������
		long transCount = Long.parseLong(statisRepository.getTransCount());
		//ÿСʱ������
		long transAveHour = transCount / hour;
		//����ÿСʱ���׷�ֵ
		statisRepository.updateMaxHourCount(String.valueOf(hourCount));
		//����ÿСʱ������
		Statis statis = new Statis();
		statis.setStatisKey("transAveHour");
		statis.setStatisValue(String.valueOf(transAveHour));
		statisRepository.save(statis);
		statisRepository.flush();
	}
}
