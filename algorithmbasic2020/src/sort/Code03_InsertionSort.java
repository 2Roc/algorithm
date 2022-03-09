package sort;

import util.GenerateRandomArray;

import java.util.Arrays;

/**
 * 把数组看成一个有序表和一个无序表
 * 一开始有序中只有一个元素，无序表中有n-1个元素
 * 依次将无序表中的数据和有序表中的比较，找到合适的位置
 *
 */
public class Code03_InsertionSort {

	public static void insertionSort(int[] arr) {
		if (arr == null || arr.length < 2){
			return;
		}
		// 不只1个数
		for (int i = 1; i < arr.length; i++) { // 0 ~ i 做到有序
			for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
				swap(arr, j, j + 1);
			}
		}
	}

	// i和j是一个位置的话，会出错
	public static void swap(int[] arr, int i, int j) {
		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100; // 随机数组的长度0～100
		int maxValue = 100;// 值：-100～100
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr = GenerateRandomArray.generateRandomArray(maxSize, maxValue);
			int[] arr1 = Arrays.copyOf(arr,arr.length);
			int[] arr2 = Arrays.copyOf(arr,arr.length);
			insertionSort(arr1);
			Arrays.sort(arr2);
			if (!GenerateRandomArray.isEqual(arr1, arr2)) {
				// 打印arr1
				// 打印arr2
				succeed = false;
				for (int k : arr) {
					System.out.print(k + " ");
				}
				System.out.println();
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = GenerateRandomArray.generateRandomArray(maxSize, maxValue);
		GenerateRandomArray.printArray(arr);
		insertionSort(arr);
		GenerateRandomArray.printArray(arr);
	}
}
