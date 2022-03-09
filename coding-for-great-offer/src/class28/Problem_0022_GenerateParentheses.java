package class28;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出n对括号，请编写一个函数来生成所有的由n对括号组成的合法组合。
 * 例如，给出n=3，解集为：
 * "((()))", "(()())", "(())()", "()()()", "()(())"
 */
public class Problem_0022_GenerateParentheses {

	ArrayList<String> ans = new ArrayList<>();
	public ArrayList<String> generateParenthesis(int n) {
		char[] path = new char[n << 1];

		process(path, 0, 0, n);
		return ans;
	}


	// path 做的决定  path[0....index-1]做完决定的！
	// path[index.....] 还没做决定，当前轮到index位置做决定！
	public void process(char[] path, int index, int leftMinusRight, int leftRest) {
		if (index == path.length) {
			ans.add(String.valueOf(path));
		} else {
			// index (   )
			if (leftRest > 0) {
				path[index] = '(';
				process(path, index + 1, leftMinusRight + 1, leftRest - 1);
			}
			if (leftMinusRight > 0) {
				path[index] = ')';
				process(path, index + 1, leftMinusRight - 1, leftRest);
			}
		}
	}

	// 不剪枝的做法
	public static List<String> generateParenthesis2(int n) {
		char[] path = new char[n << 1];
		List<String> ans = new ArrayList<>();
		process2(path, 0, ans);
		return ans;
	}

	public static void process2(char[] path, int index, List<String> ans) {
		if (index == path.length) {
			if (isValid(path)) {
				ans.add(String.valueOf(path));
			}
		} else {
			path[index] = '(';
			process2(path, index + 1, ans);
			path[index] = ')';
			process2(path, index + 1, ans);
		}
	}

	public static boolean isValid(char[] path) {
		int count = 0;
		for (char cha : path) {
			if (cha == '(') {
				count++;
			} else {
				count--;
			}
			if (count < 0) {
				return false;
			}
		}
		return count == 0;
	}

}
