package com.springboot.base.module.controller;

import com.springboot.base.common.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;
import com.springboot.base.common.UserPermissionHelp;
import com.springboot.base.common.user.entity.User;
import com.springboot.base.module.service.ActivityService;
@Controller
@RequestMapping("activityService")
public class ActivityController extends BaseController{

	private static Logger log = Logger.getLogger(ActivityController.class);
	@Resource
	private ActivityService activityService;
	@Resource
	private UserPermissionHelp userPermissionHelp;
	@Resource
	private BaseController baseController;

}