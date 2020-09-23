package com.qishui.basic;

public class ArrTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] arr = { 10, 11, 22, 1, 22, 33, 44, 5, 3, 8 };
		System.out.println("最大值:" + getMax(arr));

		int[] newArr = sortArr(arr);

		System.out.print(stringArr(newArr));

	}

	public static int getMax(int[] arr) {

		if (arr == null) {
			return 0;
		}

		// 找最大值
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] >= max) {
				max = arr[i];
			}
		}
		return max;

	}

	/**
	 * 排序
	 * 
	 * @param arr
	 * @return
	 */
	public static int[] sortArr(int[] arr) {

		if (arr == null) {
			return null;
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] > arr[i]) {
					int temp = 0;
					temp = arr[j];
					arr[j] = arr[i];
					arr[i] = temp;
				}
			}
		}
		return arr;

	}

	public static String stringArr(int[] arr) {
		
		if (arr == null) {
			return null;
		}
		StringBuffer sb=new StringBuffer();
		sb.append("[");
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
			if(i< arr.length-1) {
				sb.append(", ");	
			}
		}
		sb.append("]");
		return sb.toString();
		
	}

}
