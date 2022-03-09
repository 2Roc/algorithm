package sort;

import util.GenerateRandomArray;

import java.util.Arrays;

public class Code01_SelectionSort {

	public static void selectionSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		// 0 ~ N-1  找到最小值，在哪，放到0位置上
		// 1 ~ n-1  找到最小值，在哪，放到1 位置上
		// 2 ~ n-1  找到最小值，在哪，放到2 位置上
		for (int i = 0; i < arr.length - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < arr.length; j++) {
				// 这一句的目的就是i ~ N-1 上找最小值的下标
				minIndex = arr[j] < arr[minIndex] ? j : minIndex;
			}
			swap(arr, i, minIndex);
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		int[] arr1 = GenerateRandomArray.generateRandomArray(maxSize, maxValue);
		int[] arr2 = Arrays.copyOf(arr1,arr1.length);
		long start = System.currentTimeMillis();
		for (int i = 0; i < testTime; i++) {

			selectionSort(arr1);
			Arrays.sort(arr2);
			if (!Arrays.equals(arr1, arr2)) {
				succeed = false;
				GenerateRandomArray.printArray(arr1);
				GenerateRandomArray.printArray(arr2);
				break;
			}
		}
		System.out.println("选择排序花费"+(System.currentTimeMillis()-start)+"秒");
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = GenerateRandomArray.generateRandomArray(maxSize, maxValue);
		GenerateRandomArray.printArray(arr);
		selectionSort(arr);
		GenerateRandomArray.printArray(arr);
	}
}
