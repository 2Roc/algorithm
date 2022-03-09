package class10;

/**
 *  K个逆序对数组
 * 给出两个整数n和k，找出所有包含从1到n的数字，且恰好拥有k个逆序对的不同的数组的个数。
 * 逆序对的定义如下：对于数组的第i个和第j个元素，如果满i<j且a[i]>a[j]，则其为一个逆序对；否则不是。
 * 由于答案可能很大，只需要返回 答案 mod 10^9+ 7 的值。
 * https://leetcode.com/problems/k-inverse-pairs-array/
 */
public class Code03_KInversePairs {

	public static int kInversePairs(int n, int k) {
		if (n < 1 || k < 0) {
			return 0;
		}
		int[][] dp = new int[n + 1][k + 1];
		dp[0][0] = 1;
		int mod = 1000000007;
		for (int i = 1; i <= n; i++) {
			dp[i][0] = 1;
			for (int j = 1; j <= k; j++) {
				dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % mod;
				if (j >= i) {
					dp[i][j] = (dp[i][j] - dp[i - 1][j - i] + mod) % mod;
				}
			}
		}
		return dp[n][k];
	}

	public static int kInversePairs2(int n, int k) {
		if (n < 1 || k < 0) {
			return 0;
		}
		// dp[i][j]: 1~i的数字恰好形成j个逆序对的组合数
		int[][] dp = new int[n + 1][k + 1];
		dp[0][0] = 1;
		for (int i = 1; i <= n; i++) {
			// 1 ~ n，形成0个逆序对的方案数均为1
			dp[i][0] = 1;
			for (int j = 1; j <= k; j++) {
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
				if (j >= i) {
					dp[i][j] -= dp[i - 1][j - i];
				}
			}
		}
		return dp[n][k];
	}

	//时间复杂度O（NK），空间OK）
	public int kInversePairs3(int n, int k) {
		// dp[i][j] = dp[i-1][j] + dp[i-1][j-1] + ... + dp[i-1][j-i-1]
		// dp[i][j-1] =            dp[i-1][j-1] + ... + dp[i-1][j-1-(i-1-1)] + dp[i-1][j-1-i-1]
		// dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1-i-1]

		int MOD = 1000000007;
		long[][] dp = new long[2][k + 1];
		dp[1][0] = 1;
		for (int i = 2; i <= n; i++) {
			for (int j = 0; j <= Math.min(k, i * (i - 1) / 2); j++) {
				// dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1-i-1]
				dp[i & 1][j] = ((j >= 1 ? dp[i & 1][j - 1] : 0) +
						dp[(i - 1) & 1][j] - (j >= i ? dp[(i - 1) & 1][j - i] : 0) + MOD) % MOD;
			}
		}

		return (int) dp[n & 1][k];
	}
}
