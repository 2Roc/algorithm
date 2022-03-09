package sort;

import util.GenerateRandomArray;

import java.util.Arrays;

/**
 * 计数排序
 */
public class Code02_CountSort {

	// only for 0~200 value
	public static void countSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int max = Integer.MIN_VALUE;
		for (int value : arr) {
			max = Math.max(max, value);
		}
		int[] bucket = new int[max + 1];
		for (int k : arr) {
			bucket[k]++;
		}
		int i = 0;
		for (int j = 0; j < bucket.length; j++) {
			while (bucket[j]-- > 0) {
				arr[i++] = j;
			}
		}
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 150;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = GenerateRandomArray.generateRandomArray(maxSize, maxValue);
			int[] arr2 = Arrays.copyOf(arr1,arr1.length);
			countSort(arr1);
			Arrays.sort(arr2);
			if (!GenerateRandomArray.isEqual(arr1, arr2)) {
				succeed = false;
				GenerateRandomArray.printArray(arr1);
				GenerateRandomArray.printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = GenerateRandomArray.generateRandomArray(maxSize, maxValue);
		GenerateRandomArray.printArray(arr);
		countSort(arr);
		GenerateRandomArray.printArray(arr);
	}
}
