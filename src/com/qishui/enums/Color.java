package com.qishui.enums;

//特殊类
public enum Color {

	// 枚举值 常量
	// public static final Color red=new Color() ;
	// public static final Color yellow=new Color() ;
	// red, yellow;

	// public static final Color red=new Color("黄色") ;
	// 枚举值位于第一行
	red("红色"), yellow("黄色");

	// 可以实现抽象方法
	String color;

	private Color(String color) {
		this.color = color;
	}

	public String getColor() {
		return color;
	}

}
