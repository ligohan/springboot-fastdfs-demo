package com.gitnavi.springboot.springbootsimpledemo.pojo.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.Version;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@ApiModel("基础对象")
@Data
public class SuperEntity<T extends Model> extends Model<T> implements Serializable {

	private static final long serialVersionUID = 7560570481504032191L;


	@TableId
	private String id;


	@ApiModelProperty("创建时间")
	private Date createDate;


	@ApiModelProperty("乐观锁标识")
	@Version
	private Long lockVersion;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
