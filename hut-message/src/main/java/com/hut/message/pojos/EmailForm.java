package com.hut.message.pojos;

import java.util.List;

public class EmailForm {
	
	private String subject;
	private List<String> address;
	private boolean html;//
	private String displayName;
	private String content;

	//该项值，只在日志模式下，直接push 到 MediaLog的 extraLog 字段中
	private String extraLog;
	
	public EmailForm(){
		this.html= true;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<String> getAddress() {
		return address;
	}

	public void setAddress(List<String> address) {
		this.address = address;
	}

	public boolean isHtml() {
		return html;
	}

	public void setHtml(boolean html) {
		this.html = html;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getExtraLog() {
		return extraLog;
	}

	public void setExtraLog(String extraLog) {
		this.extraLog = extraLog;
	}
	
}
