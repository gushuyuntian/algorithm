package com.study.algorithm.sort.quicksort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author 孙雪超
 * @date 2016年4月5日 下午2:24:23
 */
public class QuickSort {

	/**
	 * 排序
	 * 
	 * @param a
	 * @param left
	 * @param right
	 * @return a
	 */
	public int[] sort(int[] a, int left, int right) {
		if (left < right) {
			int low = left;
			int high = right;
			int pivot = getPivot(a, low, high);
			while (low < high) {
				while (low < high && a[high] >= pivot) {
					high--;
				}
				while (low < high && a[low] <= pivot) {
					low++;
				}
				if (a[low] > a[high]) {
					a[low] = a[low] + a[high];
					a[high] = a[low] - a[high];
					a[low] = a[low] - a[high];
				}
			}
			sort(a, left, low - 1);
			sort(a, low + 1, right);
		}
		return a;
	}

	public void sort1(int[] n, int left, int right) {
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
			sort1(n, left, low - 1);
			sort1(n, high + 1, right);
		}
	}

	/**
	 * 获取基准
	 * 
	 * @param a
	 * @param lo
	 * @param hi
	 * 
	 * @return 基准
	 */
	public int getPivot(int[] a, int lo, int hi) {
		int min = a[lo];
		int middle = a[(lo + hi) / 2];
		int max = a[hi];

		if (min > middle) {
			min = min + middle;
			middle = min - middle;
			min = min - middle;
		}

		if (middle > max) {
			middle = middle + max;
			max = middle - max;
			middle = middle - max;
		}

		if (min > middle) {
			min = min + middle;
			middle = min - middle;
			min = min - middle;
		}
		return middle;
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

	public static void main(String[] args) {
		QuickSort quick = new QuickSort();
		int a[] = new int[100];
		Random rd = new Random();
		for (int i = 0; i < a.length; i++) {
			a[i] = rd.nextInt(a.length);
		}

		int a2[] = Arrays.copyOf(a, a.length);

		long start1 = System.currentTimeMillis();
		 quick.sort1(a, 0, a.length - 1);
		System.out.println(System.currentTimeMillis() - start1);
		for (int i : a) {
			System.out.print(i + " ");
		}

		System.out.println();
		long start2 = System.currentTimeMillis();
		quick.quicksort(a2, 0, a2.length - 1);
		System.out.println(System.currentTimeMillis() - start2);

		for (int i : a2) {
			System.out.print(i + " ");
		}

	}
}
