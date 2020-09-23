package com.qishui.file;

/**
 * 小中大规则数据
 * 
 * @author zhou
 *
 */
public enum Size {

	min(1), mid(5), max(10);

	int length;

	private Size(int length) {
		this.length = length;
	}

	public int getLength() {
		return length;
	}

}
