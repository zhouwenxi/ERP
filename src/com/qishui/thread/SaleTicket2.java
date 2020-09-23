package com.qishui.thread;

import com.qishui.utils.Utils;

public class SaleTicket2 implements Runnable {


	// 将数据保留一份
	int sum = 30;

	// 线程安全问题: 出现两个线程以上使用共享资源，
	@Override
	public void run() {
		while (true) {
			synchronized (SaleTicket2.class) {
				if (sum > 0) {
					Utils.out(Thread.currentThread().getName(),"卖出了第", (31-sum), "张票 .");
					sum--;
				} else {
					Utils.out("已经卖完了... .");
					break;
				}
			}
		}

	}

}
