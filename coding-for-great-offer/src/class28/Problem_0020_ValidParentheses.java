package class28;

import java.util.Stack;

/**
 * 匹配括号
 */
public class Problem_0020_ValidParentheses {

	public static boolean isValid(String s) {
		if (s == null || s.length() == 0) {
			return true;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		char[] stack = new char[N];
		int size = 0;
		for (char cha : str) {
			if (cha == '(' || cha == '[' || cha == '{') {
				stack[size++] = cha == '(' ? ')' : (cha == '[' ? ']' : '}');
			} else {
				if (size == 0) {
					return false;
				}
				char last = stack[--size];
				if (cha != last) {
					return false;
				}
			}
		}
		return size == 0;
	}

	public boolean isValid2 (String s) {
		Stack<Character> stack = new Stack<>();
		if(s == null)return false;
		for(char m:s.toCharArray()){
			if('('==m)
				stack.push(')');
			else if('['==m)
				stack.push(']');
			else if('{'==m)
				stack.push('}');
			else if(stack.isEmpty() || stack.pop()!=m)
				return false;
		}
		return stack.isEmpty();
	}

}
