package com.qishui.model.decorator.demo1;

/**
 * 画图+上涂料
 * @author zhou
 *
 */
public class Mather implements Work {

	public Son son;

	public Mather(Son son) {
		this.son = son;
	}

	/**
	 * 画画
	 */
	public void work() {
		son.work();
		System.out.println("涂料...");
	}



}