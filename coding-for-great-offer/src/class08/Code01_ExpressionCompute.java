package class08;

import java.util.*;

// 本题测试链接 : https://leetcode.com/problems/basic-calculator-iii/
public class Code01_ExpressionCompute {

	public static int calculate(String str) {
		return f(str.toCharArray(), 0)[0];
	}

	// 请从str[i...]往下算，遇到字符串终止位置或者右括号，就停止
	// 返回两个值，长度为2的数组
	// 0) 负责的这一段的结果是多少
	// 1) 负责的这一段计算到了哪个位置
	public static int[] f(char[] str, int i) {
		LinkedList<String> que = new LinkedList<>();
		int cur = 0;
		int[] bra;
		// 从i出发，开始撸串
		while (i < str.length && str[i] != ')') {
			if (str[i] >= '0' && str[i] <= '9') {//遇到数字字符
				cur = cur * 10 + str[i++] - '0';
			} else if (str[i] != '(') { // 遇到的是运算符号
				addNum(que, cur);
				que.addLast(String.valueOf(str[i++]));
				cur = 0;
			} else { // 遇到左括号了
				bra = f(str, i + 1);
				cur = bra[0];
				i = bra[1] + 1;
			}
		}
		addNum(que, cur);
		return new int[] { getNum(que), i };
	}

	public static void addNum(LinkedList<String> que, int num) {
		if (!que.isEmpty()) {
			int cur = 0;
			String top = que.pollLast();
			if (top.equals("+") || top.equals("-")) {
				que.addLast(top);
			} else {
				cur = Integer.parseInt(que.pollLast());
				num = top.equals("*") ? (cur * num) : (cur / num);
			}
		}
		que.addLast(String.valueOf(num));
	}

	public static int getNum(LinkedList<String> que) {
		int res = 0;
		boolean add = true;
		String cur;
		int num = 0;
		while (!que.isEmpty()) {
			cur = que.pollFirst();
			if (cur.equals("+")) {
				add = true;
			} else if (cur.equals("-")) {
				add = false;
			} else {
				num = Integer.parseInt(cur);
				res += add ? num : (-num);
			}
		}
		return res;
	}

	//宫水三叶的双栈解法
	static Map<Character, Integer> map = new HashMap<>() {{
		put('-', 1);
		put('+', 1);
		put('*', 2);
	}};

	public static int solve(String s) {
		// 将所有的空格去掉
		s = s.replaceAll(" ", "");

		char[] cs = s.toCharArray();
		int n = s.length();

		// 存放所有的数字
		Deque<Integer> nums = new ArrayDeque<>();
		// 为了防止第一个数为负数，先往 nums 加个 0
		nums.addLast(0);
		// 存放所有「非数字以外」的操作
		Deque<Character> ops = new ArrayDeque<>();

		for (int i = 0; i < n; i++) {
			char c = cs[i];
			if (c == '(') {
				ops.addLast(c);
			} else if (c == ')') {
				// 计算到最近一个左括号为止
				while (!ops.isEmpty()) {
					if (ops.peekLast() != '(') {
						calc(nums, ops);
					} else {
						ops.pollLast();
						break;
					}
				}
			} else {
				if (isNumber(c)) {
					int u = 0;
					int j = i;
					// 将从 i 位置开始后面的连续数字整体取出，加入 nums
					while (j < n && isNumber(cs[j])) u = u * 10 + (cs[j++] - '0');
					nums.addLast(u);
					i = j - 1;
				} else {
					if (i > 0 && (cs[i - 1] == '(' || cs[i - 1] == '+' || cs[i - 1] == '-')) {
						nums.addLast(0);
					}
					// 有一个新操作要入栈时，先把栈内可以算的都算了
					// 只有满足「栈内运算符」比「当前运算符」优先级高/同等，才进行运算
					while (!ops.isEmpty() && ops.peekLast() != '(') {
						char prev = ops.peekLast();
						if (map.get(prev) >= map.get(c)) {
							calc(nums, ops);
						} else {
							break;
						}
					}
					ops.addLast(c);
				}
			}
		}
		// 将剩余的计算完
		while (!ops.isEmpty() && ops.peekLast() != '(') calc(nums, ops);
		return nums.peekLast();
	}
	// 计算逻辑：从 nums 中取出两个操作数，从 ops 中取出运算符，然后根据运算符进行计算即可
	static void calc(Deque<Integer> nums, Deque<Character> ops) {
		if (nums.isEmpty() || nums.size() < 2) return;
		if (ops.isEmpty()) return;
		int b = nums.pollLast(), a = nums.pollLast();
		char op = ops.pollLast();
		int ans = 0;
		if (op == '+') ans = a + b;
		else if (op == '-') ans = a - b;
		else if (op == '*') ans = a * b;
		nums.addLast(ans);
	}
	static boolean isNumber(char c) {
		return Character.isDigit(c);
	}


	public int[] FindNumsAppearOnce (int[] array) {
		if(array.length==2)return array;
		HashMap<Integer,Integer> map  = new HashMap<>();
		for(int m=0;m<array.length;m++){
			int count = map.getOrDefault(m,0)+1;
			if(count > 2)continue;
			map.put(array[m],count);
		}
		int[] ans = new int[2];
		for(int m:map.values()){
			if(m==1)
				ans = 	new int[]{m};
		}
		return ans;
	}
	public static void main(String[] args) {
		String test = "52+13*(5+2-(5*(23-4)))-40+((3*2)+(5/2))";
		System.out.println(calculate(test));
		System.out.println(solve(test));
	}
}