package com.springboot.base.module.entity;

import com.springboot.base.common.base.entity.BaseEntity;
public class MallFeedback extends BaseEntity{

	private Integer id;

	private Integer mallMpUserId;

	private String content;

	private String reply;


	public MallFeedback(){
		super();
	}

	public MallFeedback(Integer id,Integer mallMpUserId,String content,String reply){
		super();
		this.id = id;
		this.mallMpUserId = mallMpUserId;
		this.content = content;
		this.reply = reply;
	}

	public Integer getId(){
		return id;
	}
	public Integer getMallMpUserId(){
		return mallMpUserId;
	}
	public String getContent(){
		return content;
	}
	public String getReply(){
		return reply;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public void setMallMpUserId(Integer mallMpUserId){
		this.mallMpUserId = mallMpUserId;
	}
	public void setContent(String content){
		this.content = content;
	}
	public void setReply(String reply){
		this.reply = reply;
	}
}