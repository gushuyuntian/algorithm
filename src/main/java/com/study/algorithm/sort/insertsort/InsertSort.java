package com.study.algorithm.sort.insertsort;

import com.study.algorithm.sort.ArrayUtils;

/**
 * @author 孙雪超
 * @date 2016年4月7日 下午5:10:21 <br/>
 *       介绍：
 * 
 *       插入排序（Insertion Sort）的算法描述是一种简单直观的排序算法。 <br/>
 *       它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描 ，找到相应位置并插入。 <br/>
 *       插入排序在实现上 ，通常采用in-place排序（即只需用到O(1)的额外空间的排序）， <br/>
 *       因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位 ，为最新元素提供插入空间。
 * 
 *       步骤：
 * 
 *       从第一个元素开始，该元素可以认为已经被排序 取出下一个元素，在已经排序的元素序列中从后向前扫描 <br/>
 *       如果该元素（已排序）大于新元素，将该元素移到下一位置 重复步骤3， <br/>
 *       直到找到已排序的元素小于或者等于新元素的位置 将新元素插入到该位置中 重复步骤2
 */
public class InsertSort {

	static void sort(int[] n) {
		for (int i = 1; i < n.length; i++) {
			int cur = n[i];
			for (int j = 0; j < i; j++) {
				if (cur < n[j]) {
					int k = i - 1;
					while (k >= j) {
						n[k + 1] = n[k--];
					}
					n[j] = cur;
					break;
				}
			}
		}
	}

	static void sort2(int[] n) {

		for (int i = 1; i < n.length; i++) {
			int cur = n[i];
			int pos = i;
			for (int j = i - 1; j >= 0; j--) {
				if (cur < n[j]) {
					n[j + 1] = n[j];
					pos--;
				} else {
					break;
				}
			}
			n[pos] = cur;
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
