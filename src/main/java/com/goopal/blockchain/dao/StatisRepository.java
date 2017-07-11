package com.goopal.blockchain.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.goopal.blockchain.dto.Statis;

public interface StatisRepository extends JpaRepository<Statis, String>, JpaSpecificationExecutor<Statis>
{
//	@Query("select count(a), sum(a.amount), sum(a.fee) from Transaction a")
//	public Object[] getStatisForTrans();
	
	@Query("select a from Statis a where a.statisKey in ('transCount', 'transAmount', 'transFee')")
	public List<Statis> getStatisForBlockTrans();
	
	@Query("select a.statisValue from Statis a where a.statisKey = 'transCount' ")
	public String getTransCount();
	
	@Query("select count(a) from Transaction a where a.timestamp like ?1 ")
	public long getCountByHour(String time);
	
	@Modifying
	@Transactional
	@Query("update Statis a set a.statisValue = ?1 where a.statisKey = 'maxHourCount' and a.statisValue < ?1 ")
	public void updateMaxHourCount(String maxHourCount);
}
