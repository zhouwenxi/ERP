package com.qishui.thread;

import com.qishui.utils.Utils;

public class QiThread  extends Thread{
	
	
	@Override
	public void run() {
		
		super.run();
	
		for (int i = 0; i < 10; i++) {			
			Utils.out(this.getName(),"子线程:",i);
		}
		
	}

}
