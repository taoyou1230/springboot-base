package com.springboot.base.module.controller;

import com.springboot.base.common.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;
import com.springboot.base.common.UserPermissionHelp;
import com.springboot.base.common.user.entity.User;
import com.springboot.base.module.service.MallFeedbackService;
@Controller
@RequestMapping("mallFeedbackService")
public class MallFeedbackController extends BaseController{

	private static Logger log = Logger.getLogger(MallFeedbackController.class);
	@Resource
	private MallFeedbackService mallFeedbackService;
	@Resource
	private UserPermissionHelp userPermissionHelp;
	@Resource
	private BaseController baseController;

}