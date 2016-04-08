package com.study.algorithm.sort.mergesort;

import com.study.algorithm.sort.ArrayUtils;

/**
 * @author 孙雪超
 * @date 2016年4月6日 下午3:41:13 <br/>
 *       介绍：
 * 
 *       归并排序（Merge sort，台湾译作：合并排序）是建立在归并操作上的一种有效的排序算法。<br/>
 *       该算法是采用分治法（Divide and Conquer）的一个非常典型的应用<br/>
 *       归并排序算法稳定，数组需要O(n)的额外空间，链表需要O(log(n))的额外空间，时间复杂度为O(nlog(n)) <br/>
 *       步骤：
 * 
 *       1.申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列<br/>
 *       2. 设定两个指针，最初位置分别为两个已经排序序列的起始位置<br/>
 *       3. 比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置 <br/>
 *       4.重复步骤3直到某一指针达到序列尾 <br/>
 *       5.将另一序列剩下的所有元素直接复制到合并序列尾
 */
public class MergeSort {

	static void mergeSort(int[] n, int left, int right) {
		if (left >= right)
			return;
		int center = (left + right) / 2;
		mergeSort(n, left, center);
		mergeSort(n, center + 1, right);
		merge(n, left, center, right);
	}

	static void merge(int[] n, int left, int center, int right) {
		int[] tmpArr = new int[right - left + 1];
		int i = left;
		int j = center + 1;
		int k = 0;
		while (i <= center && j <= right) {
			tmpArr[k++] = n[i] < n[j] ? n[i++] : n[j++];
		}
		while (i <= center) {
			tmpArr[k++] = n[i++];
		}
		while (j <= right) {
			tmpArr[k++] = n[j++];
		}
		for (int k2 = 0; k2 < tmpArr.length; k2++) {
			n[left + k2] = tmpArr[k2];
		}
	}

	static void print(int[] n) {
		for (int i : n)
			System.out.print(i + " ");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int a[] = ArrayUtils.random(100, 0);
		mergeSort(a, 0, a.length - 1);
		ArrayUtils.printArray(a);
	}

}
