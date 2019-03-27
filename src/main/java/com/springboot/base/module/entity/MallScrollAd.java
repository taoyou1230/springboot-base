package com.springboot.base.module.entity;

import com.springboot.base.common.base.entity.BaseEntity;
public class MallScrollAd extends BaseEntity{

	private Integer id;

	private String name;

	private String description;

	private String url;

	private String companyName;


	public MallScrollAd(){
		super();
	}

	public MallScrollAd(Integer id,String name,String description,String url,String companyName){
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.url = url;
		this.companyName = companyName;
	}

	public Integer getId(){
		return id;
	}
	public String getName(){
		return name;
	}
	public String getDescription(){
		return description;
	}
	public String getUrl(){
		return url;
	}
	public String getCompanyName(){
		return companyName;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public void setUrl(String url){
		this.url = url;
	}
	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}
}