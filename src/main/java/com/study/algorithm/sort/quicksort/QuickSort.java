package com.study.algorithm.sort.quicksort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author 孙雪超
 * @date 2016年4月5日 下午2:24:23 <br/>
 *       介绍：
 * 
 *       快速排序是由东尼·霍尔所发展的一种排序算法。在平均状况下，排序 n 个项目要Ο(n log n) 次比较。<br/>
 *       在最坏状况下则需要Ο(n2)次比较，但这种状况并不常见。事实上，快速排序通常明显比其他Ο(n log n) 算法更快，<br/>
 *       因为它的内部循环（inner loop）可以在大部分的架构上很有效率地被实现出来，且在大部分真实世界的数据，<br/>
 *       可以决定设计的选择，减少所需时间的二次方项之可能性。
 * 
 *       步骤：
 * 
 *       1.从数列中挑出一个元素，称为 “基准”（pivot）.<br/>
 *       2.重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边
 *       ）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作。 <br/>
 *       3.递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 */
public class QuickSort {

	/**
	 * @param n
	 * @param left
	 * @param right
	 */
	public void sort(int[] n, int left, int right) {
		if (left < right) {
			int pivot = n[left];
			int low = left;
			int high = right;
			while (low < high) {
				while (low < high && n[high] >= pivot)
					high--;
				if (low < high)
					n[low] = n[high];
				while (low < high && n[low] <= pivot)
					low++;
				if (low < high)
					n[high] = n[low];
			}
			n[low] = pivot;
			sort(n, left, low - 1);
			sort(n, high + 1, right);
		}
	}

	static void quicksort(int n[], int left, int right) {
		int dp;
		if (left < right) {
			dp = partition(n, left, right);
			quicksort(n, left, dp - 1);
			quicksort(n, dp + 1, right);
		}
	}

	static int partition(int n[], int left, int right) {
		int pivot = n[left];
		while (left < right) {
			while (left < right && n[right] >= pivot)
				right--;
			if (left < right)
				n[left++] = n[right];
			while (left < right && n[left] <= pivot)
				left++;
			if (left < right)
				n[right--] = n[left];
		}
		n[left] = pivot;
		return left;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		QuickSort quick = new QuickSort();
		int a[] = new int[100];
		Random rd = new Random();
		for (int i = 0; i < a.length; i++) {
			a[i] = rd.nextInt(a.length);
		}

		int a2[] = Arrays.copyOf(a, a.length);

		long start1 = System.currentTimeMillis();
		quick.sort(a, 0, a.length - 1);
		System.out.println(System.currentTimeMillis() - start1);
		for (int i : a) {
			System.out.print(i + " ");
		}

		System.out.println();
		long start2 = System.currentTimeMillis();
		QuickSort.quicksort(a2, 0, a2.length - 1);
		System.out.println(System.currentTimeMillis() - start2);

		for (int i : a2) {
			System.out.print(i + " ");
		}

	}
}
