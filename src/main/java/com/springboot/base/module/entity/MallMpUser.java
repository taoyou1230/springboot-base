package com.springboot.base.module.entity;

import com.springboot.base.common.base.entity.BaseEntity;
public class MallMpUser extends BaseEntity{

	private Integer id;

	private String openid;

	private String sessionKey;

	private String name;

	private String address;

	private String avatar;


	public MallMpUser(){
		super();
	}

	public MallMpUser(Integer id,String openid,String sessionKey,String name,String address,String avatar){
		super();
		this.id = id;
		this.openid = openid;
		this.sessionKey = sessionKey;
		this.name = name;
		this.address = address;
		this.avatar = avatar;
	}

	public Integer getId(){
		return id;
	}
	public String getOpenid(){
		return openid;
	}
	public String getSessionKey(){
		return sessionKey;
	}
	public String getName(){
		return name;
	}
	public String getAddress(){
		return address;
	}
	public String getAvatar(){
		return avatar;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public void setOpenid(String openid){
		this.openid = openid;
	}
	public void setSessionKey(String sessionKey){
		this.sessionKey = sessionKey;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public void setAvatar(String avatar){
		this.avatar = avatar;
	}
}