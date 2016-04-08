package com.study.algorithm.sort.bubblesort;

import com.study.algorithm.sort.ArrayUtils;

/**
 * @author 孙雪超
 * @date 2016年4月7日 下午4:58:36<br/>
 *       介绍：
 * 
 *       冒泡排序（Bubble Sort，台湾译为：泡沫排序或气泡排序）是一种简单的排序算法。<br/>
 *       它重复地走访过要排序的数列，一次比较两个元素， 如果他们的顺序错误就把他们交换过来 。<br/>
 *       走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。<br/>
 *       这个算法的名字由来是因为越小的元素会经由交换慢慢 “浮”到数列的顶端。
 * 
 *       步骤：
 * 
 *       比较相邻的元素。如果第一个比第二个大，就交换他们两个。
 *       对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。<br/>
 *       针对所有的元素重复以上的步骤，除了最后一个。 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 */
public class BubbleSort {

	static void sort(int[] n) {
		for (int i = 0; i < n.length - 1; i++) { // n.length-1次冒泡
			for (int j = i + 1; j < n.length; j++) {
				if (n[i] > n[j]) {
					n[i] = n[i] + n[j];
					n[j] = n[i] - n[j];
					n[i] = n[i] - n[j];
				}
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
