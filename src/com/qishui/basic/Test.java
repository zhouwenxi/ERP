package com.qishui.basic;

/***
 * test
 * 
 * @author zhou
 *
 */
public class Test {

	public static void main(String[] args) {

		downLine(0);

		int i = 0;
		for (i = 0; i < 100; i++) {
			System.out.println("Hello world !" + i);
		}

		int j = 0;
		while (j < 100) {
			j++;
			System.out.println("Hello world !" + j);

		}

		i--;

		if (i == j) {
			System.out.println("Hello world ! i==j i=" + i + " j=" + j);
		} else {
			System.out.println("Hello world ! i!=j i=" + i + " j=" + j);
		}

		downLine(8);

		int k = swi(2);

		sys(swi(4)," ",k," ",k);

	}

	private static void sys(Object... obj) {

		if (obj == null || obj.length == 0) {
			System.out.println("");
			return;
		}

		StringBuffer sb = new StringBuffer();
		sb.append("");

		for (int i = 0; i < obj.length; i++) {
			sb.append(obj[i]);
		}
		System.out.println(sb.toString());

	}

	private static void downLine(int l) {
		if (l <= 0) {
			l = 1;
		}

		if (l > 10) {
			l = 10;
		}

		while (l > 0) {
			System.out.println( l + "_____________________________________________");
			l--;
		}
	}

	private static int swi(int i) {
		switch (i) {

		default:
			return 2;
		case 0:
			return 1020;
		case 1:
			return 1022;
		case 2:
			return 1024;
		}

	}
}
