package com.springboot.base.module.service.impl;

import com.springboot.base.common.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.springboot.base.module.service.ActivityService;
import com.springboot.base.module.entity.Activity;
import com.springboot.base.module.dao.ActivityMapper;
@Service
public class ActivityServiceImpl extends BaseServiceImpl<Activity> implements ActivityService{

	@Resource
	private ActivityMapper activityMapper;

}