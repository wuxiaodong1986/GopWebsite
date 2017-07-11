package com.goopal.blockchain.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bc_statis")
public class Statis 
{
	@Id
	private String statisKey;
	
	private String statisValue;

	public String getStatisKey() {
		return statisKey;
	}

	public void setStatisKey(String statisKey) {
		this.statisKey = statisKey;
	}

	public String getStatisValue() {
		return statisValue;
	}

	public void setStatisValue(String statisValue) {
		this.statisValue = statisValue;
	}
}
