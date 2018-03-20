package com.gitnavi.springboot.springbootsimpledemo.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.gitnavi.springboot.springbootsimpledemo.SpringbootBaseDemoApplication;
import com.gitnavi.springboot.springbootsimpledemo.pojo.entity.SysUser;
import com.gitnavi.springboot.springbootsimpledemo.pojo.entity.enums.DeleteEnum;
import com.gitnavi.springboot.springbootsimpledemo.util.GenerateIdUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootBaseDemoApplication.class)
public class SysUserMapperTest {

	@Resource
	private SysUserMapper sysUserMapper;

	//=================================================================================

	@Test
	public void findAll() {
		List<SysUser> users = sysUserMapper.findAll();
		assertThat(users.size(), greaterThan(0));
	}

	@Test
	public void findAllBySelectSQL() {
		List<SysUser> users = sysUserMapper.findAllBySelectSQL();
		assertThat(users.size(), greaterThan(0));
	}

	@Test
	public void findById() {
		SysUser sysUser = sysUserMapper.selectById("374933329427959808");
		assertThat(sysUser, is(notNullValue()));
	}

	@Test
	public void insertObject() {
		SysUser sysUser = new SysUser();
		sysUser.setLoginName("gitnavi");
		sysUser.setDeleteEnum(DeleteEnum.DELETED);
		sysUser.setEmail("judas.n@qq.com");
		sysUser.setCreateDate(new Date());
		sysUser.setId(GenerateIdUtil.INSTANCE.getId());
		Integer resultNum = sysUserMapper.insert(sysUser);
		assertThat(resultNum, greaterThan(0));
	}

	@Test
	public void selectPage() {
		List list = sysUserMapper.selectPage(new Page<>(1, 3), null);
		assertThat(list.size(), greaterThan(0));
	}

	@Test
	public void updateById() {
		SysUser sysUser = sysUserMapper.selectById("374933329427959801");
		assertEquals(sysUser.getLoginName(), "judasn1");

		SysUser newSysUser = new SysUser();

		BeanUtils.copyProperties(sysUser, newSysUser);

		newSysUser.setLoginName("judasn11111");

		Integer resultNum = sysUserMapper.updateById(newSysUser);
		assertThat(resultNum, greaterThan(0));

		SysUser newSelectSysUser = sysUserMapper.selectById("374933329427959801");
		assertEquals(newSelectSysUser.getLoginName(), "judasn11111");
	}

}