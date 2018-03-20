package com.gitnavi.springboot.springbootsimpledemo.pojo.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum DeleteEnum implements IEnum {

	NOT_DELETE(0, "未删除"),

	DELETED(1, "已删除");

	//=================================================================================

	private int value;
	private String desc;

	DeleteEnum(final int value, final String desc) {
		this.value = value;
		this.desc = desc;
	}

	@Override
	public Serializable getValue() {
		return this.value;
	}

	@JsonValue
	public String getDesc() {
		return this.desc;
	}

	public int getIntegerValue() {
		return this.value;
	}
}
