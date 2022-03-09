package class03;

import java.util.Stack;

public class Code06_TwoStacksImplementQueue {

	public static class TwoStacksQueue<T> {
		public Stack<T> stackPush;
		public Stack<T> stackPop;

		public TwoStacksQueue() {
			stackPush = new Stack<>();
			stackPop = new Stack<>();
		}
		//先向一个栈实现

		// push栈向pop栈倒入数据
		private void pushToPop() {
//			if (stackPop.empty()) {
				while (!stackPush.empty()) {
					stackPop.push(stackPush.pop());
				}
//			}
		}

		public void add(T pushInt) {
			stackPush.push(pushInt);
//			pushToPop();
		}

		public T poll() {
			if (stackPop.empty() && stackPush.empty()) {
				throw new RuntimeException("Queue is empty!");
			}
			pushToPop();
			return stackPop.pop();
		}

		public T peek() {
			if (stackPop.empty() && stackPush.empty()) {
				throw new RuntimeException("Queue is empty!");
			}
			pushToPop();
			return stackPop.peek();
		}
	}

	public static void main(String[] args) {
		TwoStacksQueue test = new TwoStacksQueue();
		test.add("dfsd");
		System.out.println(test.peek());
		System.out.println(test.poll());
		test.add(0x110);
		System.out.println(test.peek());
		System.out.println(test.poll());
		System.out.println(test.peek());
		System.out.println(test.poll());
	}
}
