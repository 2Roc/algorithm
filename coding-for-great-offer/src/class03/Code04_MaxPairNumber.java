package class03;

import util.GenerateRandomArray;

import java.util.Arrays;

// 给定一个数组arr，代表每个人的能力值。再给定一个非负数k。
// 如果两个人能力差值正好为k，那么可以凑在一起比赛，一局比赛只有两个人
// 返回最多可以同时有多少场比赛
public class Code04_MaxPairNumber {

	// 暴力解
	public static int maxPairNum1(int[] arr, int k) {
		if (k < 0) {
			return -1;
		}
		return process1(arr, 0, k);
	}

	public static int process1(int[] arr, int index, int k) {
		int ans = 0;
		if (index == arr.length) {
			for (int i = 1; i < arr.length; i += 2) {
				if (arr[i] - arr[i - 1] == k) {
					ans++;
				}
			}
		} else {
			for (int r = index; r < arr.length; r++) {
				swap(arr, index, r);
				ans = Math.max(ans, process1(arr, index + 1, k));
				swap(arr, index, r);
			}
		}
		return ans;
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// 时间复杂度O(N*logN)
	public static int maxPairNum2(int[] arr, int k) {
		if (k < 0 || arr == null || arr.length < 2) {
			return 0;
		}
		Arrays.sort(arr);
		int ans = 0;
		int N = arr.length;
		int L = 0;
		int R = 0;
		boolean[] usedR = new boolean[N];
		while (L < N && R < N) {
			if (usedR[L]) {
				L++;
			} else if (L >= R) {
				R++;
			} else { // 不止一个数，而且都没用过！
				int distance = arr[R] - arr[L];
				if (distance == k) {
					ans++;
					usedR[R++] = true;
					L++;
				} else if (distance < k) {
					R++;
				} else {
					L++;
				}
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		int maxLen = 10;
		int maxValue = 20;
		int maxK = 5;
		int testTime = 1000;
		System.out.println("功能测试开始");
		for (int i = 0; i < testTime; i++) {
			int N = (int) (Math.random() * (maxLen + 1));
			int[] arr = GenerateRandomArray.generateRandomArray(N, maxValue);
			int[] arr1 = Arrays.copyOf(arr,arr.length);
			int[] arr2 = Arrays.copyOf(arr,arr.length);
			int k = (int) (Math.random() * (maxK + 1));
			int ans1 = maxPairNum1(arr1, k);
			int ans2 = maxPairNum2(arr2, k);
			if (ans1 != ans2) {
				System.out.println("Oops!");
				GenerateRandomArray.printArray(arr);
				System.out.println(k);
				System.out.println(ans1);
				System.out.println(ans2);
				break;
			}
		}
		System.out.println("功能测试结束");
	}
}
