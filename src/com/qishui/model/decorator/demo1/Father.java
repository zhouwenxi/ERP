package com.qishui.model.decorator.demo1;

/**
 * 画图+上涂料+上图框
 * 
 * @author zhou
 *
 */
public class Father implements Work {

	public Mather mather;

	public Father(Mather mather) {
		this.mather = mather;
	}

	@Override
	public void work() {
		// TODO Auto-generated method stub
		mather.work();
		System.out.println("上画框...");
	}

}
