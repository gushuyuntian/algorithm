package com.study.algorithm.search;

import java.util.Arrays;

/**
 * 二分查找法
 * 
 * @author 孙雪超
 *
 */
public class BinarySearch {

	public static int rank(int key, int[] a) {
		int lo = 0;
		int hi = a.length - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (key < a[mid])
				hi = mid - 1;
			else if (key > a[mid])
				lo = mid + 1;
			else
				return mid;
		}
		return -1;
	}

	public static int rank(int key, int[] a, int lo, int hi) {
		if (lo > hi)
			return -1;
		int mid = lo + (hi - lo) / 2;
		if (key < a[mid])
			return rank(key, a, lo, mid - 1);
		else if (key > a[mid])
			return rank(key, a, mid + 1, hi);
		else
			return mid;
	}

	public static double sqrt(double c) {
		if (c < 0)
			return Double.NaN;
		double err = 1e-15;
		double t = c;
		while (Math.abs(t - c / t) > err * t) {
			t = (c / t + t) / 2.0;
			System.out.println(t);
		}
		return t;
	}

	public static void main(String[] args) {
		// int[] whiteList = new int[] { 1, 7, 8, 9, 3, 4, 2, 5, 6, 10 };
		// Arrays.sort(whiteList);
		//
		// int key = 4;
		// int pos = BinarySearch.rank(key, whiteList, 0, 9);
		// System.out.println(pos);
		//
		// int N = 100;
		// StdDraw.setXscale(0, N);
		// StdDraw.setYscale(0, N * N);
		// StdDraw.setPenRadius(.01);
		// for (int i = 1; i <= N; i++) {
		// StdDraw.point(i, i);
		// StdDraw.point(i, i * i);
		// StdDraw.point(i, i * Math.log(i));
		// }
		//
		sqrt(8.0);
	}

}
