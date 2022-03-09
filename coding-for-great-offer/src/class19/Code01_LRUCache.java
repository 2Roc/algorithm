package class19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

// 本题测试链接 : https://leetcode.com/problems/lru-cache/
// 提交时把类名和构造方法名改成 : LRUCache
public class Code01_LRUCache {

	public Code01_LRUCache(int capacity) {
		cache = new MyCache<>(capacity);
	}

	private MyCache<Integer, Integer> cache;

	public int get(int key) {
		Integer ans = cache.get(key);
		return ans == null ? -1 : ans;
	}

	public void put(int key, int value) {
		cache.set(key, value);
	}

	public static class Node<K, V> {
		public K key;
		public V value;
		public Node<K, V> last;
		public Node<K, V> next;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	public static class NodeDoubleLinkedList<K, V> {
		private Node<K, V> head;
		private Node<K, V> tail;

		public NodeDoubleLinkedList() {
			head = null;
			tail = null;
		}

		// 现在来了一个新的node，请挂到尾巴上去
		public void addNode(Node<K, V> newNode) {
			if (newNode == null) {
				return;
			}
			if (head == null) {
				head = newNode;
				tail = newNode;
			} else {
				tail.next = newNode;
				newNode.last = tail;
				tail = newNode;
			}
		}

		// node 入参，一定保证！node在双向链表里！
		// node原始的位置，左右重新连好，然后把node分离出来
		// 挂到整个链表的尾巴上
		public void moveNodeToTail(Node<K, V> node) {
			if (tail == node) {
				return;
			}
			if (head == node) {
				head = node.next;
				head.last = null;
			} else {
				node.last.next = node.next;
				node.next.last = node.last;
			}
			node.last = tail;
			node.next = null;
			tail.next = node;
			tail = node;
		}

		public Node<K, V> removeHead() {
			if (head == null) {
				return null;
			}
			Node<K, V> res = head;
			if (head == tail) {
				head = null;
				tail = null;
			} else {
				head = res.next;
				res.next = null;
				head.last = null;
			}
			return res;
		}
	}

	public static class MyCache<K, V> {
		private HashMap<K, Node<K, V>> keyNodeMap;
		private NodeDoubleLinkedList<K, V> nodeList;
		private final int capacity;

		public MyCache(int cap) {
			keyNodeMap = new HashMap<K, Node<K, V>>();
			nodeList = new NodeDoubleLinkedList<K, V>();
			capacity = cap;
		}

		public V get(K key) {
			if (keyNodeMap.containsKey(key)) {
				Node<K, V> res = keyNodeMap.get(key);
				nodeList.moveNodeToTail(res);
				return res.value;
			}
			return null;
		}

		// set(Key, Value)
		// 新增  更新value的操作
		public void set(K key, V value) {
			if (keyNodeMap.containsKey(key)) {
				Node<K, V> node = keyNodeMap.get(key);
				node.value = value;
				nodeList.moveNodeToTail(node);
			} else { // 新增！
				Node<K, V> newNode = new Node<K, V>(key, value);
				keyNodeMap.put(key, newNode);
				nodeList.addNode(newNode);
				if (keyNodeMap.size() == capacity + 1) {
					removeMostUnusedCache();
				}
			}
		}

		private void removeMostUnusedCache() {
			Node<K, V> removeNode = nodeList.removeHead();
			keyNodeMap.remove(removeNode.key);
		}
	}

	/**
	 * lru design[牛客网]
	 * 输入：
	 [[1,1,1],[1,2,2],[1,3,2],[2,1],[1,4,4],[2,2]],3
	 返回值：
	 [1,-1]
	 说明：
	 [1,1,1]，第一个1表示opt=1，要set(1,1)，即将(1,1)插入缓存，缓存是{"1"=1}
	 [1,2,2]，第一个1表示opt=1，要set(2,2)，即将(2,2)插入缓存，缓存是{"1"=1,"2"=2}
	 [1,3,2]，第一个1表示opt=1，要set(3,2)，即将(3,2)插入缓存，缓存是{"1"=1,"2"=2,"3"=2}
	 [2,1]，第一个2表示opt=2，要get(1)，返回是[1]，因为get(1)操作，缓存更新，缓存是{"2"=2,"3"=2,"1"=1}
	 [1,4,4]，第一个1表示opt=1，要set(4,4)，即将(4,4)插入缓存，但是缓存已经达到最大容量3，移除最不经常使用的{"2"=2}，插入{"4"=4}，缓存是{"3"=2,"1"=1,"4"=4}
	 [2,2]，第一个2表示opt=2，要get(2)，查找不到，返回是[1,-1]
	 * @param operators int整型二维数组 the ops
	 * @param k int整型 the k
	 * @return int整型一维数组
	 */
	public int[] LRU (int[][] operators, int k) {
		LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>(k,0.75f,true);
		List<Integer> ans = new ArrayList<>();
		for (int[] o : operators) {
			if (o[0] == 1) {
				if (map.size() == k) {
					map.remove(map.keySet().iterator().next());
				}
				map.put(o[1], o[2]);
			} else {
				if (map.get(o[1]) == null) {
					ans.add(-1);
				} else {
					ans.add(map.get(o[1]));
				}
			}
		}
		return ans.stream().mapToInt(v -> v).toArray();
	}
}
