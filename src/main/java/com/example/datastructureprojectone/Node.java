package com.example.datastructureprojectone;



public class Node<T extends Comparable<T>> {
	protected T data;
	protected Node<T> next;
	

	public Node(T data) {
		this.data = data;
		this.next = null;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}
	

}
