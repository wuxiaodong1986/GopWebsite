package com.goopal.blockchain.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BlockchainTool 
{
	/**
	 * ��ȡ��ǰʱ����Ϊid
	 */
	public static long getId()
	{
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String now = df.format(new Date());
		long id = Long.parseLong(now);
		
		return id;
	}
}
