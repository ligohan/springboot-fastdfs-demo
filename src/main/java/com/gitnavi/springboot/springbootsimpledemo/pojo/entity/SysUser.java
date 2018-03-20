package com.gitnavi.springboot.springbootsimpledemo.pojo.entity;

import com.gitnavi.springboot.springbootsimpledemo.pojo.entity.enums.DeleteEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel("用户对象")
@Data
public class SysUser extends SuperEntity<SysUser> {

	private static final long serialVersionUID = 8514016091254814311L;

	@ApiModelProperty("用户登录名")
	private String loginName;

	@ApiModelProperty("用户邮箱")
	private String email;

	@ApiModelProperty("是否删除")
	private DeleteEnum deleteEnum;

}