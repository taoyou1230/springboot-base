package com.springboot.base.module.service.impl;

import com.springboot.base.common.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.springboot.base.module.service.MallMpUserService;
import com.springboot.base.module.entity.MallMpUser;
import com.springboot.base.module.dao.MallMpUserMapper;
@Service
public class MallMpUserServiceImpl extends BaseServiceImpl<MallMpUser> implements MallMpUserService{

	@Resource
	private MallMpUserMapper mallMpUserMapper;

}