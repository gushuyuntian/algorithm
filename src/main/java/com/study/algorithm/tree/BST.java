package com.study.algorithm.tree;

import java.util.PriorityQueue;
import java.util.Queue;

public class BST<Key extends Comparable<Key>, Value> {

	private Node root;

	private class Node {
		public Key key;
		public Value value;
		public Node left, right;
		public int N;

		public Node(Key key, Value value, int N) {
			super();
			this.key = key;
			this.value = value;
			this.N = N;
		}

	}

	public int size() {
		return size(root);
	}

	private int size(Node node) {
		if (node == null)
			return 0;
		return node.N;
	}

	public Value get(Key key) {
		return get(root, key);
	}

	private Value get(Node node, Key key) {
		if (node == null)
			return null;
		int cmp = key.compareTo(node.key);
		if (cmp > 0) {
			return get(node.right, key);
		} else if (cmp < 0) {
			return get(node.left, key);
		} else
			return node.value;
	}

	public void put(Key key, Value value) {
		root = put(root, key, value);
	}

	private Node put(Node node, Key key, Value value) {
		if (node == null) {
			node = new Node(key, value, 1);
		}
		int cmp = key.compareTo(node.key);
		if (cmp > 0) {
			node.right = put(node.right, key, value);
		} else if (cmp < 0) {
			node.left = put(node.left, key, value);
		} else {
			node.value = value;
		}
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}

	public Key min() {
		return min(root).key;
	}

	public Node min(Node node) {
		if (node.left == null) {
			return node;
		}
		return min(node.left);
	}

	public Key max() {
		return max(root).key;
	}

	public Node max(Node node) {
		if (node.right == null)
			return node;
		return max(node.right);
	}

	public Key floor(Key key) {
		return floor(root, key).key;
	}

	private Node floor(Node node, Key key) {
		if (node == null)
			return null;
		int cmp = key.compareTo(node.key);
		if (cmp == 0)
			return node;
		if (cmp < 0) {
			return floor(node.left, key);
		}
		Node t = floor(node.right, key);
		if (t != null) {
			return t;
		} else {
			return node;
		}
	}

	public Key select(int k) {
		return select(root, k).key;
	}

	private Node select(Node node, int k) {
		if (node == null) {
			return null;
		}
		int t = size(node.left);
		if (t > k) {
			return select(node.left, k);
		} else if (t < k) {
			return select(node.right, k - t - 1);
		} else
			return node;
	}

	public int rank(Key key) {
		return rank(key, root);
	}

	private int rank(Key key, Node node) {
		if (node == null)
			return 0;
		int cmp = key.compareTo(node.key);
		if (cmp < 0) {
			return rank(key, node.left);
		} else if (cmp > 0) {
			return 1 + size(node.left) + rank(key, node.right);
		} else
			return size(node.left);

	}

	public void deleteMin() {
		root = deleteMin(root);
	}

	private Node deleteMin(Node x) { // 删除此分支下最小节点，并返回
		if (x.left == null) {
			return x.right;
		}
		x.left = deleteMin(x.left);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	public void delete(Key key) {
		root = delete(root, key);
	}

	private Node delete(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			x.left = delete(x.left, key);
		} else if (cmp > 0) {
			x.right = delete(x.right, key);
		} else {
			if (x.right == null) {
				return x.left;
			}
			if (x.left == null) {
				return x.right;
			}
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	public Iterable<Key> keys() {
		return keys(min(), max());
	}

	public Iterable<Key> keys(Key lo, Key hi) {
		Queue<Key> queue = new PriorityQueue<Key>();
		keys(root, queue, lo, hi);
		return queue;
	}

	public void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
		if (x == null)
			return;
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
		if (cmplo < 0)
			keys(x.left, queue, lo, hi);
		if (cmplo <= 0 && cmphi >= 0)
			queue.add(x.key);
		if (cmphi > 0)
			keys(x.right, queue, lo, hi);

	}
}
