package com.realtimetpo.entities;

public class Mailing {
	
	public String username;
	public String password;
	public String subject;
	public String text;
	private String attachment;
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUsername() {
		//System.out.println(username);
		return username;
	}
	public void setUsername(String username) {
		
		this.username = username;
		System.out.println(this.username);
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
