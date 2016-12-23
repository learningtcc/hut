package com.hut.message.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hut.common.model.PojoPersistent;

import java.util.Date;

public class Account implements PojoPersistent{

	private int id;
	private Date createdAt;
	private boolean trash;
	
	private String host;
	private String accountName;
	@JsonIgnore
	private String accountPassword;
	private String accountType;
	private long total;
	
	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
	
	private String addition;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isTrash() {
		return trash;
	}

	public void setTrash(boolean trash) {
		this.trash = trash;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountPassword() {
		return accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAddition() {
		return addition;
	}

	public void setAddition(String addition) {
		this.addition = addition;
	}
	
}
