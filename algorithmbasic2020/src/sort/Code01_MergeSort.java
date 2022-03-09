package sort;

import util.GenerateRandomArray;

import java.util.Arrays;

public class Code01_MergeSort {

	// 递归方法实现
	public static void mergeSort1(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process(arr, 0, arr.length - 1);
	}

	// 请把arr[L..R]排有序
	// l...r N
	// T(N) = 2 * T(N / 2) + O(N)
	// O(N * logN)
	public static void process(int[] arr, int L, int R) {
		if (L == R) { // base case
			return;
		}
		while(L<R){
			int mid = L + ((R - L) >> 1);
			process(arr, L, mid);
			process(arr, mid + 1, R);
			merge(arr, L, mid, R);
		}
	}

	public static void merge(int[] arr, int L, int M, int R) {
		int[] temp = new int[R - L + 1];//辅助数组，存放最终结果
		int i = 0;
		int p1 = L;//左边有序序列的初始索引
		int p2 = M + 1;//右边有序序列的初始索引

		while (p1 <= M && p2 <= R) {
			temp[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
		}
		// 要么p1越界了，要么p2越界了，将没越界的一方剩余数依次放到temp即可
		while (p1 <= M) {
			temp[i++] = arr[p1++];
		}
		while (p2 <= R) {
			temp[i++] = arr[p2++];
		}
		for (i = 0; i < temp.length; i++) {
			arr[L + i] = temp[i];
		}
	}

	// 非递归方法实现
	public static void mergeSort2(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int length = arr.length;
		// 步长
		int mergeSize = 1;
		while (mergeSize < length) { // log N
			// 当前左组的，第一个位置
			int L = 0;
			while (L < length) {
				if (mergeSize >= length - L) {
					break;
				}
				int M = L + mergeSize - 1;
				int R = M + Math.min(mergeSize, length - M - 1);
				merge(arr, L, M, R);
				L = R + 1;
			}
			// 防止溢出
			if (mergeSize > length / 2) {
				break;
			}
			mergeSize <<= 1;
		}
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = GenerateRandomArray.generateRandomArray(maxSize, maxValue);
			int[] arr2 = Arrays.copyOf(arr1,arr1.length);
			mergeSort1(arr1);
			mergeSort2(arr2);
			if (!GenerateRandomArray.isEqual(arr1, arr2)) {
				System.out.println("出错了！");
				GenerateRandomArray.printArray(arr1);
				GenerateRandomArray.printArray(arr2);
				break;
			}
		}
		System.out.println("测试结束");
	}
}
