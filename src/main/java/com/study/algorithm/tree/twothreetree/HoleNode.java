package com.study.algorithm.tree.twothreetree;

/**
 * A hole node does not have any values, and have only one child.
 */
final class HoleNode<T extends Comparable> extends Node {
    private Node<T> child;

    HoleNode() {
        super();
    }

    public boolean isTwoNode() {
        return false;
    }

    public Node sibling() {
        if (parent() != null) {
            return parent().leftChild() == this ? parent().rightChild(): parent().leftChild();
        }
        return null;
    }

    @Override
    public void setLeftChild(Node leftChild) {
    }

    @Override
    public void removeChildren() {
        child = null;
    }


    @Override
    public void setRightChild(Node rightChild) {
    }

    public Node<T> child() {
        return child;
    }

    public void setChild(Node<T> child) {
        this.child = child;
    }
}