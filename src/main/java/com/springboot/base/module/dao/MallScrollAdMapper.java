package com.springboot.base.module.dao;

import org.apache.ibatis.annotations.Mapper;
import com.springboot.base.utils.MyMapper;
import	com.springboot.base.module.entity.MallScrollAd;

import java.util.List;

import java.util.Map;

@Mapper
public interface MallScrollAdMapper extends MyMapper<MallScrollAd>{

	/**
	* 根据条件查询数据
	* @param map = {
	*  filters:查询条件，
	*  sortInfo：排序条件
	* }
	* @return
	*/
	public List<MallScrollAd> queryByCondition(Map<String,Object>map);//添加一条完整记录


}