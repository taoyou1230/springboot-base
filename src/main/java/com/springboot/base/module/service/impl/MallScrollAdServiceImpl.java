package com.springboot.base.module.service.impl;

import com.springboot.base.common.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.springboot.base.module.service.MallScrollAdService;
import com.springboot.base.module.entity.MallScrollAd;
import com.springboot.base.module.dao.MallScrollAdMapper;
@Service
public class MallScrollAdServiceImpl extends BaseServiceImpl<MallScrollAd> implements MallScrollAdService{

	@Resource
	private MallScrollAdMapper mallScrollAdMapper;

}