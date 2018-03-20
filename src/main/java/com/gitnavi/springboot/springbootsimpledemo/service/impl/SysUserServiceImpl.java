package com.gitnavi.springboot.springbootsimpledemo.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gitnavi.springboot.springbootsimpledemo.mapper.SysUserMapper;
import com.gitnavi.springboot.springbootsimpledemo.pojo.entity.SysUser;
import com.gitnavi.springboot.springbootsimpledemo.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {


	@Resource
	private SysUserMapper sysUserMapper;

	//=====================================业务处理 start=====================================

	@Override
	public List<SysUser> findAll() {
		return sysUserMapper.findAll();
	}
	//=====================================业务处理  end=====================================

	//=====================================私有方法 start=====================================

	//=====================================私有方法  end=====================================

}
