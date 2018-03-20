package com.gitnavi.springboot.springbootsimpledemo.service;

import com.gitnavi.springboot.springbootsimpledemo.SpringbootBaseDemoApplication;
import com.gitnavi.springboot.springbootsimpledemo.pojo.entity.SysUser;
import com.gitnavi.springboot.springbootsimpledemo.pojo.entity.enums.DeleteEnum;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootBaseDemoApplication.class)
public class SysUserServiceTest {

	@Resource
	private SysUserService sysUserService;

	//=================================================================================

	@Test
	public void findAll() {
		List<SysUser> sysUserList = sysUserService.findAll();
		assertThat(sysUserList.size(), greaterThan(0));
	}


	@Test
	public void insertOrUpdateObject() {
		String id = "374933329427959801";
		SysUser sysUser = new SysUser();
		sysUser.setId(id);
		sysUser.setLoginName("gitnavi");
		sysUser.setDeleteEnum(DeleteEnum.DELETED);
		sysUser.setEmail("judas.n@qq.com");
		sysUser.setCreateDate(new Date());
		boolean insertOrUpdateResult = sysUserService.insertOrUpdate(sysUser);
		assertThat(insertOrUpdateResult, is(true));
		SysUser newSysUser = sysUserService.selectById(id);
		assertEquals(newSysUser.getLoginName(), "gitnavi");
	}

	@Test
	public void selectPage() {
		Page<SysUser> sysUserPage = sysUserService.selectPage(new Page<>(1, 3), null);
		List<SysUser> resultList = sysUserPage.getRecords();
		int totalCount = sysUserPage.getTotal();
		int pageCount = sysUserPage.getPages();
		Assert.assertThat(resultList.size(), greaterThan(0));
		Assert.assertThat(totalCount, greaterThan(0));
		Assert.assertThat(pageCount, greaterThan(0));
	}


	@Test
	public void selectPageByParam() {
		EntityWrapper<SysUser> entityWrapper = new EntityWrapper<>();
		entityWrapper.between("create_date", "2017-01-01 00:51:28", "2017-03-03 00:51:28");
		entityWrapper.where("login_name={0}", "judasn1");
		entityWrapper.like("email", "n1");
		entityWrapper.isNotNull("email");
		entityWrapper.in("login_name", Lists.newArrayList("judasn1", "judasn2"));
		entityWrapper.orderBy("id", false);
	
	
		Page<SysUser> sysUserPage = sysUserService.selectPage(new Page<>(1, 3), entityWrapper);
		List<SysUser> resultList = sysUserPage.getRecords();
		int totalDataCount = sysUserPage.getTotal();
		int totalPageCount = sysUserPage.getPages();
		Assert.assertThat(resultList.size(), greaterThanOrEqualTo(0));
		Assert.assertThat(totalDataCount, greaterThanOrEqualTo(0));
		Assert.assertThat(totalPageCount, greaterThanOrEqualTo(0));
	}

}