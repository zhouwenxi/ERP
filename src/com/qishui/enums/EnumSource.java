package com.qishui.enums;

public class EnumSource {

	private String color;
	// 枚举类型原理
	public static EnumSource RED = new EnumSource("红色");
	public static EnumSource YELLOW = new EnumSource("黄色");
	public static EnumSource BLUE = new EnumSource("蓝色");

	public String getColor() {
		return color;
	}

	private EnumSource(String color) {
		this.color = color;
	}

}
