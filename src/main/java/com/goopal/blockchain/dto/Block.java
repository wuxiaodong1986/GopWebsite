package com.goopal.blockchain.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "bc_block")
public class Block 
{
	@Id
	private Long blockNum;
	
	private String previousSecret;
	
	private String delegateSignature;
	
	private String userTransactionIds;
	
	private String id;
	
	private Integer blockSize;
	
	private Integer latency;
	
	private String signeeSharesIssued;
	
	private Integer signeeFeesCollected;
	
	private Integer signeeFeesDestroyed;
	
	private String randomSeed;
	
	private String timestamp;

	private String signee;
	
	private Long amount;
	
	private Long fee;
	
	private Long transCount;

	@Transient
	private String preId;
	
	public Long getBlockNum() {
		return blockNum;
	}

	public void setBlockNum(Long blockNum) {
		this.blockNum = blockNum;
	}

	public String getPreviousSecret() {
		return previousSecret;
	}

	public void setPreviousSecret(String previousSecret) {
		this.previousSecret = previousSecret;
	}

	public String getDelegateSignature() {
		return delegateSignature;
	}

	public void setDelegateSignature(String delegateSignature) {
		this.delegateSignature = delegateSignature;
	}

	public String getUserTransactionIds() {
		return userTransactionIds;
	}

	public void setUserTransactionIds(String userTransactionIds) {
		this.userTransactionIds = userTransactionIds;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(Integer blockSize) {
		this.blockSize = blockSize;
	}

	public Integer getLatency() {
		return latency;
	}

	public void setLatency(Integer latency) {
		this.latency = latency;
	}

	public String getSigneeSharesIssued() {
		return signeeSharesIssued;
	}

	public void setSigneeSharesIssued(String signeeSharesIssued) {
		this.signeeSharesIssued = signeeSharesIssued;
	}

	public Integer getSigneeFeesCollected() {
		return signeeFeesCollected;
	}

	public void setSigneeFeesCollected(Integer signeeFeesCollected) {
		this.signeeFeesCollected = signeeFeesCollected;
	}

	public Integer getSigneeFeesDestroyed() {
		return signeeFeesDestroyed;
	}

	public void setSigneeFeesDestroyed(Integer signeeFeesDestroyed) {
		this.signeeFeesDestroyed = signeeFeesDestroyed;
	}

	public String getRandomSeed() {
		return randomSeed;
	}

	public void setRandomSeed(String randomSeed) {
		this.randomSeed = randomSeed;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSignee() {
		return signee;
	}

	public void setSignee(String signee) {
		this.signee = signee;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getFee() {
		return fee;
	}

	public void setFee(Long fee) {
		this.fee = fee;
	}

	public Long getTransCount() {
		return transCount;
	}

	public void setTransCount(Long transCount) {
		this.transCount = transCount;
	}

	public String getPreId() {
		return preId;
	}

	public void setPreId(String preId) {
		this.preId = preId;
	}
}
