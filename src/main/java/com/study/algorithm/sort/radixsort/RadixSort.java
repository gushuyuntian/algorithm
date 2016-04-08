package com.study.algorithm.sort.radixsort;

import com.study.algorithm.sort.ArrayUtils;

/**
 * @author 孙雪超
 * @date 2016年4月8日 上午11:21:18 <br/>
 *       基数排序，属于“分配式排序”（distribution sort），又称“桶子法”（bucket sort）或bin sort，<br/>
 *       顾名思义，它是透过键值的部份资讯，将要排序的元素分配至某些“桶”中，藉以达到排序的作用，基数排序法是属于稳定性的排序 ，<br/>
 *       其时间复杂度为O (nlog(r)m)，其中r为所采取的基数，而m为堆数，在某些时候，基数排序法的效率高于其它的稳定性排序法。
 */
public class RadixSort {

	static int[] sort(int[] number, int d) {

		int n = 1;
		int k = 0;
		int m = 1; // 控制键值排序依据在哪一位
		int[][] temp = new int[10][number.length]; // 数组的第一维表示可能的余数0-9
		int[] order = new int[10]; // 数组order[i]用来表示该位是i的数的个数
		while (m <= d) {
			for (int i = 0; i < number.length; i++) {
				int lsd = ((number[i] / n) % 10);
				temp[lsd][order[lsd]] = number[i];
				order[lsd]++;
			}
			for (int i = 0; i < 10; i++) {
				if (order[i] != 0)
					for (int j = 0; j < order[i]; j++) {
						number[k++] = temp[i][j];
					}
				order[i] = 0;
			}
			k = 0;
			n *= 10;
			m++;
		}
		return number;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int a[] = ArrayUtils.random(100, 0);
		sort(a, 3);
		ArrayUtils.printArray(a);
	}
}
