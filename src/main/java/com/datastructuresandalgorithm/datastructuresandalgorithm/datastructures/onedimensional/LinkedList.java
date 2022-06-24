package com.datastructuresandalgorithm.datastructuresandalgorithm.datastructures.onedimensional;

import java.util.NoSuchElementException;

public class LinkedList {

    private class Node {
        int data;
        Node next;

        Node(int d) {
            this.data = d;
            next = null;
        }
    }

    private Node head = null;
    private Node tail = null;
    private int size=0;

    public void reverse(){
        if(isEmpty()) throw new NoSuchElementException();
        Node prev = null, current = head, next;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev=current;
            current = next;
        }

        tail = head;
        head = prev;
    }

    public int getKthNodefromTheEnd(int k){
        if(k<=0) throw new  IllegalArgumentException();
        Node prev =  head;
        Node next = head;
        for (int i = 1; i < k; i++)
            next = next.next;

        while(next.next != null){
            prev = prev.next;
            next = next.next;
        }

        return prev.data;
    }

    public void printList() {
        Node n = head;
        while (n != null) {
            System.out.print(n.data + " ");
            n = n.next;
        }
        System.out.println();
    }

    public void addLast(int data) {
        Node n = new Node(data);
        size++;
        if (isEmpty()) {
            head = tail = n;
            return;
        }
        tail.next = n;
        tail = n;
    }

    public void addFirst(int data) {
        Node n = new Node(data);
        size++;
        if (isEmpty()) {
            head = tail = n;
            return;
        }
        n.next = head;
        head = n;
    }

    public int deleteFirst() {
        if (isEmpty())
            throw new NoSuchElementException();

        int firstValue = head.data;
        size--;
        if (head == tail) {
            head = tail = null;
            return firstValue;
        }

        Node second = head.next;
        head.next = null;
        head = second;
        return firstValue;
    }

    public int deleteLast() {
        if (isEmpty())
            throw new NoSuchElementException();

        int lastValue = tail.data;
        size--;
        if(head == tail){
            head = tail = null;
            return lastValue;
        }

        Node previousToTail = getPrevious(tail);
        tail = previousToTail;
        tail.next = null;
        return lastValue;
    }

    private Node getPrevious(Node node) {
        Node n = head;
        while (n != null) {
            if (n.next == node)
                return n;
            n = n.next;
        }
        return null;
    }

    public boolean contains(int data) {
        return indexOf(data) != -1;
    }

    public int indexOf(int data) {
        Node n = head;
        for (int i = 0; i < size(); i++) {
            if (n.data == data)
                return i;
            n = n.next;
        }
        return -1;
    }

    public int[] toArray(){
        int[] v = new int[size];
        Node n = head;
        for (int i = 0; i < size ; i++) {
            v[i] = n.data;
            n = n.next;
        }
        return v;
    }

    public int size() {
        return size;
    }

    private boolean isEmpty() {
        return head == null;
    }

    public int get(int index) {
        if (size() - 1 < index)
            throw new IllegalStateException();

        Node n = head;
        for (int i = 0; i < index; i++)
            n = n.next;

        return n.data;
    }

    public Node getHead() {
        return head;
    }

}
