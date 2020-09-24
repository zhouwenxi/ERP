package com.qishui.model.single;

/**
 * 单例设计模式
 * 
 * @author zhou
 *
 */
public class Single {

	private static Single single = null;

	private Single() {
	}

	public static Single getSingle() {

		if (single == null) {
			synchronized (Single.class) {

				if (single == null) {
					single = new Single();
				}
			}
		}

		return single;

	}

}
