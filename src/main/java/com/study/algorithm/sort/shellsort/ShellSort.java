package com.study.algorithm.sort.shellsort;

import com.study.algorithm.sort.ArrayUtils;

/**
 * @author 孙雪超
 * @date 2016年4月7日 下午5:51:41 <br/>
 *       介绍：
 * 
 *       希尔排序，也称递减增量排序算法，是插入排序的一种高速而稳定的改进版本。
 * 
 *       希尔排序是基于插入排序的以下两点性质而提出改进方法的：
 * 
 *       1、插入排序在对几乎已经排好序的数据操作时， 效率高， 即可以达到线性排序的效率
 * 
 *       2、但插入排序一般来说是低效的， 因为插入排序每次只能将数据移动一位>
 */
public class ShellSort {

	static void sort(int[] n) {
		int h = 1;
		int len = n.length;
		while (h < n.length / 3) {
			h = h * 3 + 1;
		}
		while (h > 0) {
			for (int i = h; i < len; i++) { // 外层通过out确定每组插入排序的第二个数据项
				// 以下代码就是对子序列进行的插入排序算法
				int cur = n[i];
				int pos = i;
				while (pos > h - 1 && n[pos - h] >= cur) {
					n[pos] = n[pos - h];
					pos -= h;
				}
				n[pos] = cur;
			}
			// 缩小间隔
			h = (h - 1) / 3;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int a[] = ArrayUtils.random(100, 0);
		sort(a);
		ArrayUtils.printArray(a);
	}
}
