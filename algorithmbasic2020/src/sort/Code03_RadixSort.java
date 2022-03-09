package sort;

import util.GenerateRandomArray;

import java.util.Arrays;

/**
 基数排序：（桶排序）
 先分配10个桶，按照个位是几，依次放入对应桶中，
 放完后，从头到尾输出桶中元素放到原来的数组中；
 再次看十位是几，依次放入对应桶中，放完后依次输出；
 依次循环，最后输出的就是排序后的结果；
 */
public class Code03_RadixSort {

	// only for no-negative value
	public static void radixSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		radixSort(arr, 0, arr.length - 1, maxbits(arr));
	}

	public static int maxbits(int[] arr) {
		int max = Integer.MIN_VALUE;
		for (int j : arr) {
			max = Math.max(max, j);
		}
		int res = 0;
		while (max != 0) {
			//求最大值有几位
			res++;
			max /= 10;
		}
		return res;
	}

	// arr[L..R]排序 , 最大值的十进制位数digit
	public static void radixSort(int[] arr, int L, int R, int digit) {
		final int radix = 10;
		int i = 0, j = 0;
		// 有多少个数准备多少个辅助空间
		int[] help = new int[R - L + 1];
		for (int d = 1; d <= digit; d++) { // 有多少位就进出几次
			// 10个空间
		    // count[0] 当前位(d位)是0的数字有多少个
			// count[1] 当前位(d位)是(0和1)的数字有多少个
			// count[2] 当前位(d位)是(0、1和2)的数字有多少个
			// count[i] 当前位(d位)是(0~i)的数字有多少个
			int[] count = new int[radix]; // count[0..9]
			for (i = L; i <= R; i++) {
				// 103  1   3
				// 209  1   9
				j = getDigit(arr[i], d);
				count[j]++;
			}
			for (i = 1; i < radix; i++) {
				count[i] = count[i] + count[i - 1];
			}
			for (i = R; i >= L; i--) {
				j = getDigit(arr[i], d);
				help[count[j] - 1] = arr[i];
				count[j]--;
			}
			for (i = L, j = 0; i <= R; i++, j++) {
				arr[i] = help[j];
			}
		}
	}

	public static int getDigit(int x, int d) {
		return ((x / ((int) Math.pow(10, d - 1))) % 10);
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100000;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = GenerateRandomArray.generateRandomArray(maxSize, maxValue);
			int[] arr2 = Arrays.copyOf(arr1,arr1.length);
			radixSort(arr1);
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
		radixSort(arr);
		GenerateRandomArray.printArray(arr);
	}
}
