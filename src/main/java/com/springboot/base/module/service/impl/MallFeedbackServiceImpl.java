package com.springboot.base.module.service.impl;

import com.springboot.base.common.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.springboot.base.module.service.MallFeedbackService;
import com.springboot.base.module.entity.MallFeedback;
import com.springboot.base.module.dao.MallFeedbackMapper;
@Service
public class MallFeedbackServiceImpl extends BaseServiceImpl<MallFeedback> implements MallFeedbackService{

	@Resource
	private MallFeedbackMapper mallFeedbackMapper;

}