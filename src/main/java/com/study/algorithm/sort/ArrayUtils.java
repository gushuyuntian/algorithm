package com.study.algorithm.sort;

import java.util.Random;

/**
 * @author 孙雪超 2016年4月8日
 */
public class ArrayUtils {

	/**
	 * @param num
	 *            随机数个数
	 * @param min
	 *            最小值
	 * @return 随机数组
	 */
	public static int[] random(int num, int min) {
		int a[] = new int[num];
		Random rd = new Random();
		for (int i = 0; i < a.length; i++) {
			a[i] = rd.nextInt(a.length) + min;
		}
		return a;
	}

	/**
	 * 打印数组
	 * 
	 * @param array
	 */
	public static void printArray(int[] array) {
		System.out.print("{");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			if (i < array.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("}");
	}

	/**
	 * 交换元素值
	 * 
	 * @param array
	 * @param index1
	 * @param index2
	 */
	public static void exchangeElements(int[] array, int index1, int index2) {
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}
}