package com.study.algorithm.sort.selectsort;

import com.study.algorithm.sort.ArrayUtils;

/**
 * @author 孙雪超
 * @date 2016年4月7日 下午4:43:21 <br/>
 *       介绍：
 * 
 *       选择排序(Selection sort)是一种简单直观的排序算法。<br/>
 *       它的工作原理如下。首先在未排序序列中找到最小元素，存放到排序序列的起始位置，<br/>
 *       然后 ，再从剩余未排序元素中继续寻找最小元素，然后放到排序序列末尾。以此类推，直到所有元素均排序完毕。
 */
public class SelectSort {

	static void sort(int[] n) {

		for (int i = 0; i < n.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < n.length; j++) {
				if (n[min] > n[j])
					min = j;
			}
			if (min != i) {
				n[min] = n[min] + n[i];
				n[i] = n[min] - n[i];
				n[min] = n[min] - n[i];
			}
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
