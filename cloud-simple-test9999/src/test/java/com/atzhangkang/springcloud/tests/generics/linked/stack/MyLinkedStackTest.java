package com.atzhangkang.springcloud.tests.generics.linked.stack;

/**
 * @author tule
 * @version 1.0
 * @date 2021/1/7
 */
public class MyLinkedStackTest<T> {

    public static class Node<U> {
        U item;

        Node<U> node;

        Node() {
            this.item = null;
            this.node = null;
        }

        Node(U item, Node<U> node) {
            this.item = item;
            this.node = node;
        }

        public boolean end() {
            return item == null && node == null;
        }
    }

    public Node<T> top = new Node();

    public void push(T item) {
        top = new Node<>(item, top);
    }

    public T pop() {
        T item = top.item;
        if (!top.end()) {
            top = top.node;
        }
        return item;
    }

    public static void main(String[] args) {

        String[] s = "a b c d".split(" ");
        MyLinkedStackTest linkedTest = new MyLinkedStackTest<>();
        for(String ss : s) {
            linkedTest.push(ss);
        }
        linkedTest.push(new Object());
        while (!linkedTest.top.end()) {
            System.out.println(linkedTest.pop());
        }
    }
}

