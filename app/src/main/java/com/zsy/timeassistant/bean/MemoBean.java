package com.zsy.timeassistant.bean;

public class MemoBean {
	private String time;
	private String content;
	private String title;
	private int id;

	public MemoBean(String time, String title, String content, int id) {
		this.time = time;
		this.title = title;
		this.content = content;
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
