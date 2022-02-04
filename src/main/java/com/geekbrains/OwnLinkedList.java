package com.geekbrains;

public class OwnLinkedList<T> {
    private long size;
    private Node head;
    private Node tail;

    public OwnLinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    public void addNode(int index, T data) {
        if (index > size) {
            System.out.println("Размер списка меньше чем заданный индекс");
            return;
        }

        Node newNode = new Node(data);
        Node current = head;

        if(index == 0) {
            Node temp = head;
            current = newNode;
            head = newNode;
            current.next = temp;
            size++;
            return;
        }

        for (int i = 1; i <= index - 1; i++) {
            current = current.next;
        }

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            Node temp = current.next;
            current.next = newNode;
            current = current.next;
            current.next = temp;
            size++;
        }
    }

    public void addNode(T data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
        }

        tail = newNode;
        size++;
    }

    public void display() {
        Node current = head;

        if (head == null) {
            System.out.println("Односвязный список пуст");
            return;
        }

        while (current != null) {
            System.out.println(current.data + " ");
            current = current.next;
        }

        System.out.println();
    }

    public void display(int index) {
        if (index > size) {
            System.out.println("Размер списка меньше чем заданный индекс");
            return;
        }

        Node current = head;

        for (int i = 1; i <= index; i++) {
            current = current.next;
        }

        System.out.println(current.data);
    }


    private class Node {
        T data;
        Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
}
