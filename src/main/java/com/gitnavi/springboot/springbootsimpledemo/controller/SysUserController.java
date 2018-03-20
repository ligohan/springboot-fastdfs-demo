package com.gitnavi.springboot.springbootsimpledemo.controller;


import com.gitnavi.springboot.springbootsimpledemo.pojo.entity.SysUser;
import com.gitnavi.springboot.springbootsimpledemo.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Api(value = "SysUserRestController RESTful", description = "用户模块管理 REST API")
@RestController
@RequestMapping("/api/sys-user")
public class SysUserController {

	@Resource
	private SysUserService sysUserService;

	//=====================================业务处理 start=====================================

	@ApiOperation(value = "查询所有用户列表", notes = "查询所有用户列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<SysUser> list() {
		List<SysUser> sysUserList = sysUserService.findAll();
		return sysUserList;
	}

	@ApiOperation(value = "添加用户", notes = "添加用户")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "sysUser", value = "用户信息", paramType = "body"),
	})
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> save(@RequestBody SysUser sysUser) {
		Map<String, Object> map = new HashMap<>();
		boolean result = sysUserService.insert(sysUser);
		map.put("添加结果", result);
		return ResponseEntity.ok(map);
	}

	//=====================================业务处理  end=====================================

	//=====================================私有方法 start=====================================

	//=====================================私有方法  end=====================================

}
