package com.goopal.blockchain.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.goopal.blockchain.dto.Block;

public interface BlockRepository extends JpaRepository<Block, Long>, JpaSpecificationExecutor<Block>
{
	@Query("select max(a.blockNum) from Block a")
	public long getMaxBlockNum();
}
