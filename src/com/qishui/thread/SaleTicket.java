package com.qishui.thread;

import com.qishui.utils.Utils;

public class SaleTicket extends Thread {

	// 自定义名字
	public SaleTicket(String name) {
		super(name);
	}

	// 将数据保留一份
	static int sum = 30;

	// 线程安全问题: 出现两个线程以上使用共享资源，
	@Override
	public void run() {
		while (true) {
			synchronized (SaleTicket.class) {
				if (sum > 0) {
					Utils.out(this.getName(), "卖出了第", sum, "张票 .");
					sum--;
				} else {
					Utils.out("已经卖完了... .");
					break;
				}
			}
		}

	}

}
