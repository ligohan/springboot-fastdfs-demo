package com.gitnavi.springboot.springbootsimpledemo.service;

import com.baomidou.mybatisplus.service.IService;
import com.gitnavi.springboot.springbootsimpledemo.pojo.entity.SysUser;

import java.util.List;


public interface SysUserService extends IService<SysUser> {


	List<SysUser> findAll();

}
