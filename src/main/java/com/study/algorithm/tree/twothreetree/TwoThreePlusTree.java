package com.study.algorithm.tree.twothreetree;

/**
 *  A class to represent the 2-3+ Tree Data Structure
 *  By: Gustavo Silva & Anil Jethani
 *  Code Made By Modifying TwoThreeTree.java
 *  Major changes have remarks
 */

import java.util.*;

@SuppressWarnings("unchecked")
public class TwoThreePlusTree<T extends Comparable> implements Dictionary<T> {

	Node<T> root;
	int size = 0;

	private Node<T> findNode(Node<T> node, T value) {
		if (node == null)
			return null;

		if (node.isThreeNode()) {
			int leftComp = value.compareTo(node.leftVal());
			int rightComp = value.compareTo(node.rightVal());
			if (leftComp == 0 || rightComp == 0) {
				return node;
			}
			if (leftComp < 0) {
				return findNode(node.leftChild(), value);
			} else if (rightComp < 0) {
				return findNode(node.middleChild(), value);
			} else {
				return findNode(node.rightChild(), value);
			}
		} else {
			int comp = value.compareTo(node.val());
			if (comp == 0)
				return node;
			if (comp < 0)
				return findNode(node.leftChild(), value);
			else
				return findNode(node.rightChild(), value);
		}
	}

	private static final class DuplicateException extends RuntimeException {
	};

	private static final DuplicateException DUPLICATE = new DuplicateException();

	private Node<T> insertHelp(T value, Node<T> node) throws DuplicateException {
		Node<T> returnValue = null;

		if (node.isTwoNode()) {
			int comp = value.compareTo(node.val());

			if (node.isLeaf()) {
				if (comp == 0)
					throw DUPLICATE;
				Node<T> threeNode = Node.newThreeNode(value, node.val());
				Node<T> parent = node.parent();
				if (parent != null)
					parent.replaceChild(node, threeNode);
				else
					root = threeNode;
			} else {
				if (comp < 0) {
					Node<T> result = insertHelp(value, node.leftChild());
					if (result != null) {
						Node<T> threeNode = Node.newThreeNode(result.val(), node.val());
						threeNode.setRightChild(node.rightChild());
						threeNode.setMiddleChild(result.rightChild());
						threeNode.setLeftChild(result.leftChild());
						if (node.parent() != null) {
							node.parent().replaceChild(node, threeNode);
						} else {
							root = threeNode;
						}
						unlinkNode(node);
					}
				} else if (comp > 0) {
					Node<T> result = insertHelp(value, node.rightChild());
					if (result != null) {
						Node<T> threeNode = Node.newThreeNode(result.val(), node.val());
						threeNode.setLeftChild(node.leftChild());
						threeNode.setMiddleChild(result.leftChild());
						threeNode.setRightChild(result.rightChild());
						if (node.parent() != null) {
							node.parent().replaceChild(node, threeNode);
						} else {
							root = threeNode;
						}
						unlinkNode(node);
					}
				} else {
					throw DUPLICATE;
				}
			}

		} else { // three node
			Node<T> threeNode = node;

			int leftComp = value.compareTo(threeNode.leftVal());
			int rightComp = value.compareTo(threeNode.rightVal());
			if (leftComp == 0 || rightComp == 0) {
				throw DUPLICATE;
			}

			if (threeNode.isLeaf()) {
				returnValue = splitLeafNode(threeNode, value);

			} else {
				if (leftComp < 0) {
					Node<T> result = insertHelp(value, threeNode.leftChild());
					if (result != null) {
						returnValue = splitInnerNode(threeNode, result.val());
						returnValue.leftChild().setLeftChild(result.leftChild());
						returnValue.leftChild().setRightChild(result.rightChild());
						returnValue.rightChild().setLeftChild(threeNode.middleChild());
						returnValue.rightChild().setRightChild((threeNode.rightChild()));
						unlinkNode(threeNode);
					}
				} else if (rightComp < 0) {
					Node<T> result = insertHelp(value, threeNode.middleChild());
					if (result != null) {
						returnValue = splitInnerNode(threeNode, result.val());
						returnValue.leftChild().setLeftChild(threeNode.leftChild());
						returnValue.leftChild().setRightChild(result.leftChild());
						returnValue.rightChild().setLeftChild(result.rightChild());
						returnValue.rightChild().setRightChild(threeNode.rightChild());
						unlinkNode(threeNode);
					}
				} else {
					Node<T> result = insertHelp(value, threeNode.rightChild());
					if (result != null) {
						returnValue = splitInnerNode(threeNode, result.val());
						returnValue.leftChild().setLeftChild(threeNode.leftChild());
						returnValue.leftChild().setRightChild(threeNode.middleChild());
						returnValue.rightChild().setLeftChild(result.leftChild());
						returnValue.rightChild().setRightChild(result.rightChild());
						unlinkNode(threeNode);
					}
				}
			}
		}
		return returnValue;
	}

	private void unlinkNode(Node node) {
		node.removeChildren();
		node.setParent(null);
	}

	private Node<T> successor(Node<T> node, T value) {
		if (node == null)
			return null;

		if (!node.isLeaf()) {
			Node<T> p;
			if (node.isThreeNode() && node.leftVal().equals(value)) {
				p = node.middleChild();
			} else {
				p = node.rightChild();
			}
			while (p.leftChild() != null) {
				p = p.leftChild();
			}
			return p;
		} else {
			Node<T> p = node.parent();
			if (p == null)
				return null;

			Node<T> ch = node;
			while (p != null && ch == p.rightChild()) {
				ch = p;
				p = p.parent();
			}
			return p != null ? p : null;
		}
	}

	private Node<T> predecessor(Node<T> node, T value) {
		if (node == null)
			return null;

		Node<T> p;
		if (!node.isLeaf()) {
			if (node.isThreeNode() && node.rightVal().equals(value)) {
				p = node.middleChild();
			} else {
				p = node.leftChild();
			}

			while (p.rightChild() != null) {
				p = p.rightChild();
			}
			return p;
		} else {
			throw new UnsupportedOperationException("Implement predecessor parent is not terminal node");
		}

	}

	/**
	 * Turned splitNode function from original file into two separate functions:
	 *
	 * splitInnerNode: Same code as original, used to split an inner node( which
	 * acts only as a placeholder for 2-3+ trees)
	 *
	 * splitLeafNode: Modified code, used to split a leaf node
	 *
	 */
	private Node<T> splitInnerNode(Node<T> threeNode, T value) {
		T min;
		T max;
		T middle;
		if (value.compareTo(threeNode.leftVal()) < 0) {
			min = value;
			middle = threeNode.leftVal();
			max = threeNode.rightVal();
		} else if (value.compareTo(threeNode.rightVal()) < 0) {
			min = threeNode.leftVal();
			middle = value;
			max = threeNode.rightVal();
		} else {
			min = threeNode.leftVal();
			max = value;
			middle = threeNode.rightVal();
		}

		Node<T> parent = Node.newTwoNode(middle);
		parent.setLeftChild(Node.newTwoNode(min));

		// Change here to make 2-3+ tree
		parent.setRightChild(Node.newTwoNode(max));
		return parent;
	}

	private Node<T> splitLeafNode(Node<T> threeNode, T value) {
		T min;
		T max;
		T middle;
		if (value.compareTo(threeNode.leftVal()) < 0) {
			min = value;
			middle = threeNode.leftVal();
			max = threeNode.rightVal();
		} else if (value.compareTo(threeNode.rightVal()) < 0) {
			min = threeNode.leftVal();
			middle = value;
			max = threeNode.rightVal();
		} else {
			min = threeNode.leftVal();
			max = value;
			middle = threeNode.rightVal();
		}

		Node<T> parent = Node.newTwoNode(middle);
		parent.setLeftChild(Node.newTwoNode(min));

		// Change here to make 2-3+ tree
		parent.setRightChild(Node.newThreeNode(middle, max));
		return parent;
	}

	/* DICTIONARY FUNCTIONS */

	@Override
	public boolean remove(T value) {
		if (value == null)
			return false;
		// System.out.println("removing " + value);
		Node<T> node = findNode(root, value);
		if (node == null)
			return false;

		HoleNode hole = null;
		Node<T> terminalNode;
		T holeValue;
		if (node.isLeaf()) {
			terminalNode = node;
			holeValue = value;
		} else {
			// Replace by successor.
			if (node.isThreeNode()) {
				if (node.leftVal().equals(value)) {
					Node<T> pred = predecessor(node, value);
					holeValue = pred.isThreeNode() ? pred.rightVal() : pred.val();
					node.setLeftVal(holeValue);
					terminalNode = pred;
				} else {
					Node<T> succ = successor(node, value);
					holeValue = succ.isThreeNode() ? succ.leftVal() : succ.val();
					node.setRightVal(holeValue);
					terminalNode = succ;
				}
			} else {
				Node<T> succ = successor(node, value);
				holeValue = succ.isThreeNode() ? succ.leftVal() : succ.val();
				node.setVal(holeValue);
				terminalNode = succ;
			}
		}

		assert terminalNode.isLeaf();

		if (terminalNode.isThreeNode()) {
			// Easy case. Replace 3-node by 2-node
			T val = terminalNode.leftVal().equals(holeValue) ? terminalNode.rightVal() : terminalNode.leftVal();
			Node<T> twoNode = Node.newTwoNode(val);
			if (terminalNode.parent() != null) {
				terminalNode.parent().replaceChild(terminalNode, twoNode);
			} else {
				root = twoNode;
			}
		} else {
			if (terminalNode.parent() != null) {
				hole = Node.newHole();
				terminalNode.parent().replaceChild(terminalNode, hole);
			} else {
				root = null;
			}
		}

		// For description of each case see
		// "2-3 Tree Deletion: Upward Phase" in
		// http://cs.wellesley.edu/~cs230/spring07/2-3-trees.pdf
		while (hole != null) {
			// Case 1. The hole has a 2-node as parent and 2-node as sibling.
			if (hole.parent().isTwoNode() && hole.sibling().isTwoNode()) {
				// System.out.println("Case 1");
				Node<T> parent = hole.parent();
				Node<T> sibling = hole.sibling();

				Node<T> threeNode = Node.newThreeNode(parent.val(), sibling.val());
				if (parent.leftChild() == hole) {
					threeNode.setLeftChild(hole.child());
					threeNode.setMiddleChild(sibling.leftChild());
					threeNode.setRightChild(sibling.rightChild());
				} else {
					threeNode.setLeftChild(sibling.leftChild());
					threeNode.setMiddleChild(sibling.rightChild());
					threeNode.setRightChild(hole.child());
				}

				if (parent.parent() == null) {
					unlinkNode(hole);
					root = threeNode;
					hole = null;
				} else {
					hole.setChild(threeNode);
					parent.parent().replaceChild(parent, hole);
				}
				unlinkNode(parent);
				unlinkNode(sibling);

			}
			// Case 2. The hole has a 2-node as parent and 3-node as sibling.
			else if (hole.parent().isTwoNode() && hole.sibling().isThreeNode()) {
				// System.out.println("Case 2 ");
				Node<T> parent = hole.parent();
				Node<T> sibling = hole.sibling();

				if (parent.leftChild() == hole) {
					Node<T> leftChild = Node.newTwoNode(parent.val());
					Node<T> rightChild = Node.newTwoNode(sibling.rightVal());
					parent.setVal(sibling.leftVal());
					parent.replaceChild(hole, leftChild);
					parent.replaceChild(sibling, rightChild);
					leftChild.setLeftChild(hole.child());
					leftChild.setRightChild(sibling.leftChild());
					rightChild.setLeftChild(sibling.middleChild());
					rightChild.setRightChild(sibling.rightChild());
				} else {
					Node<T> leftChild = Node.newTwoNode(sibling.leftVal());
					Node<T> rightChild = Node.newTwoNode(parent.val());
					parent.setVal(sibling.rightVal());
					parent.replaceChild(sibling, leftChild);
					parent.replaceChild(hole, rightChild);
					leftChild.setLeftChild(sibling.leftChild());
					leftChild.setRightChild(sibling.middleChild());
					rightChild.setLeftChild(sibling.rightChild());
					rightChild.setRightChild(hole.child());
				}
				unlinkNode(hole);
				unlinkNode(sibling);
				hole = null;
			}

			// Case 3. The hole has a 3-node as parent and 2-node as sibling.
			else if (hole.parent().isThreeNode()) {
				Node<T> parent = hole.parent();

				// subcase (a), hole is in the middle
				if (parent.middleChild() == hole && parent.leftChild().isTwoNode()) {
					// System.out.println("Case 3 (a) hole in the middle");
					Node<T> leftChild = parent.leftChild();
					Node<T> newParent = Node.newTwoNode(parent.rightVal());
					Node<T> newLeftChild = Node.newThreeNode(leftChild.val(), parent.leftVal());
					newParent.setLeftChild(newLeftChild);
					newParent.setRightChild(parent.rightChild());
					if (parent != root) {
						parent.parent().replaceChild(parent, newParent);
					} else {
						root = newParent;
					}

					newLeftChild.setLeftChild(leftChild.leftChild());
					newLeftChild.setMiddleChild(leftChild.rightChild());
					newLeftChild.setRightChild(hole.child());

					unlinkNode(parent);
					unlinkNode(leftChild);
					unlinkNode(hole);
					hole = null;
				}
				// subcase (b), hole is in the middle
				else if (parent.middleChild() == hole && parent.rightChild().isTwoNode()) {
					// System.out.println("Case 3(b) hole in the middle");
					Node<T> rightChild = parent.rightChild();
					Node<T> newParent = Node.newTwoNode(parent.leftVal());
					Node<T> newRightChild = Node.newThreeNode(parent.rightVal(), rightChild.val());
					newParent.setLeftChild(parent.leftChild());
					newParent.setRightChild(newRightChild);
					if (parent != root) {
						parent.parent().replaceChild(parent, newParent);
					} else {
						root = newParent;
					}
					newRightChild.setLeftChild(hole.child());
					newRightChild.setMiddleChild(rightChild.leftChild());
					newRightChild.setRightChild(rightChild.rightChild());
					unlinkNode(parent);
					unlinkNode(rightChild);
					unlinkNode(hole);
					hole = null;
				} else if (parent.middleChild().isTwoNode()) {
					Node<T> middleChild = parent.middleChild();

					// subcase (a). hole is the left child.
					if (parent.leftChild() == hole) {
						// System.out.println("Case 3 (a) hole is left child");
						Node<T> newParent = Node.newTwoNode(parent.rightVal());
						Node<T> leftChild = Node.newThreeNode(parent.leftVal(), middleChild.val());
						newParent.setLeftChild(leftChild);
						newParent.setRightChild(parent.rightChild());
						if (parent != root) {
							parent.parent().replaceChild(parent, newParent);
						} else {
							root = newParent;
						}

						leftChild.setLeftChild(hole.child());
						leftChild.setMiddleChild(middleChild.leftChild());
						leftChild.setRightChild(middleChild.rightChild());

						unlinkNode(parent);
						unlinkNode(hole);
						unlinkNode(middleChild);
						hole = null;
					}
					// subcase (a). hole is the right child.
					else if (parent.rightChild() == hole) {
						// System.out.println("Case 3 (a) hole is right child");
						Node<T> newParent = Node.newTwoNode(parent.leftVal());
						Node<T> rightChild = Node.newThreeNode(middleChild.val(), parent.rightVal());
						newParent.setRightChild(rightChild);
						newParent.setLeftChild(parent.leftChild());
						if (parent != root) {
							parent.parent().replaceChild(parent, newParent);
						} else {
							root = newParent;
						}

						rightChild.setLeftChild(middleChild.leftChild());
						rightChild.setMiddleChild(middleChild.rightChild());
						rightChild.setRightChild(hole.child());

						unlinkNode(parent);
						unlinkNode(hole);
						unlinkNode(middleChild);
						hole = null;
					}
				}

				// Case 4. The hole has a 3-node as parent and 3-node as
				// sibling.

				else if (parent.middleChild().isThreeNode()) {
					Node<T> middleChild = parent.middleChild();
					// subcase (a) hole is the left child
					if (hole == parent.leftChild()) {
						// System.out.println("Case 4 (a) hole is left child");
						Node<T> newLeftChild = Node.newTwoNode(parent.leftVal());
						Node<T> newMiddleChild = Node.newTwoNode(middleChild.rightVal());
						parent.setLeftVal(middleChild.leftVal());
						parent.setLeftChild(newLeftChild);
						parent.setMiddleChild(newMiddleChild);
						newLeftChild.setLeftChild(hole.child());
						newLeftChild.setRightChild(middleChild.leftChild());
						newMiddleChild.setLeftChild(middleChild.middleChild());
						newMiddleChild.setRightChild(middleChild.rightChild());

						unlinkNode(hole);
						unlinkNode(middleChild);
						hole = null;
					}
					// subcase (b) hole is the right child
					else if (hole == parent.rightChild()) {
						// System.out.println("Case 4 (b) hole is right child");
						Node<T> newMiddleChild = Node.newTwoNode(middleChild.leftVal());
						Node<T> newRightChild = Node.newTwoNode(parent.rightVal());
						parent.setRightVal(middleChild.rightVal());
						parent.setMiddleChild(newMiddleChild);
						parent.setRightChild(newRightChild);
						newMiddleChild.setLeftChild(middleChild.leftChild());
						newMiddleChild.setRightChild(middleChild.middleChild());
						// newMiddleChild.setParent(middleChild.middleChild());
						newRightChild.setLeftChild(middleChild.rightChild());
						newRightChild.setRightChild(hole.child());

						unlinkNode(hole);
						unlinkNode(middleChild);
						hole = null;

					} else if (hole == parent.middleChild() && parent.leftChild().isThreeNode()) {
						// System.out.println("Case 4 (a) hole is middle child, left is 3-node");
						Node<T> leftChild = parent.leftChild();
						Node<T> newLeftChild = Node.newTwoNode(leftChild.leftVal());
						Node<T> newMiddleChild = Node.newTwoNode(parent.leftVal());
						parent.setLeftVal(leftChild.rightVal());
						parent.setLeftChild(newLeftChild);
						parent.setMiddleChild(newMiddleChild);
						newLeftChild.setLeftChild(leftChild.leftChild());
						newLeftChild.setRightChild(leftChild.middleChild());
						newMiddleChild.setLeftChild(leftChild.rightChild());
						newMiddleChild.setRightChild(hole.child());

						unlinkNode(hole);
						unlinkNode(leftChild);
						hole = null;
					} else {
						assert (hole == parent.middleChild() && parent.rightChild().isThreeNode());
						// System.out.println("Case 4 (b) hole is middle child, right is 3-node");
						Node<T> rightChild = parent.rightChild();
						Node<T> newRightChild = Node.newTwoNode(rightChild.rightVal());
						Node<T> newMiddleChild = Node.newTwoNode(parent.rightVal());
						parent.setRightVal(rightChild.leftVal());
						parent.setMiddleChild(newMiddleChild);
						parent.setRightChild(newRightChild);
						newRightChild.setRightChild(rightChild.rightChild());
						newRightChild.setLeftChild(rightChild.middleChild());
						newMiddleChild.setRightChild(rightChild.leftChild());
						newMiddleChild.setLeftChild(hole.child());

						unlinkNode(hole);
						unlinkNode(rightChild);
						hole = null;
					}
				}

			}
		}

		size--;
		return true;
	}

	@Override
	public T removeAny() {
		return null;
	}

	@Override
	public T find(T k) {
		return null;
	}

	@Override
	public void clear() {
		root = null;
	}

	@Override
	public boolean insert(T value) {
		if (root == null)
			root = Node.newTwoNode(value);
		else {
			try {
				Node<T> result = insertHelp(value, root);
				if (result != null) {
					root = result;
				}
			} catch (DuplicateException e) {
				System.out.println("Cannot enter duplicates.");
				return false;
			}
		}
		size++;
		return true;

	}

	@Override
	public int size() {
		return size;
	}

	/**
	 * Print and printLevel functions Created by: Gustavo Silva & Anil Jethani
	 * Print: Original function to call in order to print tree. printLevel:
	 * recursive helper function that prints the nodes by level for an accurate
	 * representation.
	 */
	@Override
	public void print() {
		root.print();
		System.out.println();
		ArrayList<Node> nodeList = new ArrayList<>(0);
		nodeList.clear();
		nodeList.add(root);

		// Call recursive function to print each level
		printLevel(nodeList);
	}

	private void printLevel(ArrayList<Node> nodeList) {
		if (nodeList.size() == 0)
			return;
		else {

			int count = nodeList.size();
			Node tempNode;
			ArrayList<Node> tempNodeList = new ArrayList<>(0);

			for (int i = 0; i < count; i++) {
				tempNode = nodeList.get(i);

				if (tempNode.leftChild() != null) {
					tempNode.leftChild().print();
					tempNodeList.add(tempNode.leftChild());
				}
				if (tempNode.middleChild() != null) {
					tempNode.middleChild().print();
					tempNodeList.add(tempNode.middleChild());
				}
				if (tempNode.rightChild() != null) {
					tempNode.rightChild().print();
					tempNodeList.add(tempNode.rightChild());
				}
			}

			System.out.println();
			printLevel(tempNodeList);
		}
	}

}
