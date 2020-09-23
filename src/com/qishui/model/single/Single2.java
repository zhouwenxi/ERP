package com.qishui.model.single;

/**
 * 单例设计模式
 * @author zhou
 *
 */
public class Single2 {
	
	private static Single2 single=new Single2();
	private Single2() {}
	
	public static Single2 getSingle() {
		
		return single;
		
	}
	

}
