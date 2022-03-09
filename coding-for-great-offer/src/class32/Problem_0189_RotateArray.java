package class32;

public class Problem_0189_RotateArray {

	//好理解——三次翻转
	public void rotate1(int[] nums, int k) {
		int N = nums.length;
		k = k % N;
		reverse(nums, 0, N - k - 1);
		reverse(nums, N - k, N - 1);
		reverse(nums, 0, N - 1);
	}

	public static void reverse(int[] nums, int L, int R) {
		while (L < R) {
			int tmp = nums[L];
			nums[L++] = nums[R];
			nums[R--] = tmp;
		}
	}

	public static int[] rotate2(int[] nums, int k) {
		int N = nums.length;
		k = k % N;
		if (k == 0) {
			return nums;
		}
		int L = 0;
		int R = N - 1;
		int lpart = N - k;
		int rpart = k;
		int same = Math.min(lpart, rpart);
		int diff = lpart - rpart;
		exchange(nums, L, R, same);
		while (diff != 0) {
			if (diff > 0) {
				L += same;
				lpart = diff;
			} else {
				R -= same;
				rpart = -diff;
			}
			same = Math.min(lpart, rpart);
			diff = lpart - rpart;
			exchange(nums, L, R, same);
		}
		return nums;
	}

	public static void exchange(int[] nums, int start, int end, int size) {
		int i = end - size + 1;
		int tmp = 0;
		while (size-- != 0) {
			tmp = nums[start];
			nums[start] = nums[i];
			nums[i] = tmp;
			start++;
			i++;
		}
	}

	/**
	 * 三次翻转实际上对原数组执行的赋值操作为 2N次，我这里给出一个 N次 操作的方案。
	 * 题目的置换大概可以写为： f(a) = f( (a+m) % n)
	 * 对于这样的简单位移置换，可以把数组划分为一到数个置换群来解决。
	 * 置换群确定以后，每个置换群内部的轮换操作就可以一个一个顺着放。记录置换群的头结点判断
	 * 是否该群轮换完成，采用操作计数的方法简单地确定所有数字置换完毕。如果该群轮换完成但
	 * 操作数还没达到n，代表还有置换群，则头节点加一设置新的置换群即可
	 */
	public static int[] rotate3 (int n, int m, int[] a) {
		m = m % n;
		if (m == 0)
			return a;

		int start = 0;
		int nxt = (start + m) % n;
		int count = 0;
		int pre = a[start];

		while(count < n) {
			int tmp = a[nxt];
			a[nxt] = pre;
			pre = tmp;
			nxt = (nxt + m) % n;
			if (nxt == (start + m) % n) {
				nxt ++;
				start = nxt % n;
				pre = a[start];
				nxt = (nxt + m) % n;
			}
			count ++;
		}
		return a;
	}

	public static void main(String[] args) {
		int[] aa = {1,2,3,4,5,6};
//		int[] vv = rotate2(aa, 3);
		int[] xx = rotate3(6, 3, aa);
		for(int m=0;m<aa.length;m++){
//			System.out.println(vv[m]+"^^");
			System.out.println(xx[m]+"**");
		}

	}

}
