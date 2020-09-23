package com.qishui.basic;

import com.qishui.utils.Utils;

public class Outer {

	String name;

	public Outer(String name) {
		this.name = name;
	}

	class Inner {

		public void ml() {
			Utils.out(name + "正在快乐中....");
		}

	}

	static class Inner2 {
		
		public static int flag=1;

		public void ml2() {
			Utils.out("两个人正在快乐中....");
		}

	}

}
