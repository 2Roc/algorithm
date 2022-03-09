package class29;

public class Problem_0062_UniquePaths {

	// m 行
	// n 列
	// 下：m-1
	// 右：n-1
	public static int uniquePaths(int m, int n) {
		int right = n - 1;
		int all = m + n - 2;
		long o1 = 1;
		long o2 = 1;
		// o1乘进去的个数 一定等于 o2乘进去的个数
		for (int i = right + 1, j = 1; i <= all; i++, j++) {
			o1 *= i;
			o2 *= j;
			long gcd = gcd(o1, o2);
			o1 /= gcd;
			o2 /= gcd;
		}
		return (int) o1;
	}

	// 调用的时候，请保证初次调用时，m和n都不为0
	public static long gcd(long m, long n) {
		return n == 0 ? m : gcd(n, m % n);
	}

	//额外空间O（1），时间是O（min（m，n））
	public static int uniquePaths2 (int m, int n) {
		if(m==1||n==1)return 1;
		int k = m-1+n-1;
		int v = Math.min(m-1,n-1);

		long ans = 1;
		for(int i=k;i>k-v;i--){
			ans *= i;
		}
		for(int i=1;i<=v;i++){
			ans /= i;
		}
		return (int)ans;
	}

	private static int uniquePaths3(int m, int n){
		long ans =1;
		for(int x=n,y=1;y<m;++x,++y){
			ans = ans * x /y;
		}
		return (int)ans;
	}

	public static void main(String[] args) {
		long start1 = System.currentTimeMillis();
		System.out.println(uniquePaths(40, 40));
		System.out.println("花费:"+(System.currentTimeMillis()-start1));

		long start2 = System.currentTimeMillis();
		System.out.println(uniquePaths2(40, 40));
		System.out.println("花费:"+(System.currentTimeMillis()-start2));

		long start3 = System.currentTimeMillis();
		System.out.println(uniquePaths3(40, 40));
		System.out.println("花费:"+(System.currentTimeMillis()-start3));
	}

}
