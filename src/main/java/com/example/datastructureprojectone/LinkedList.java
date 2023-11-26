package com.example.datastructureprojectone;



public class LinkedList<T extends Comparable<T>> {

	protected Node<T> head;

	public Node<T> getHead() {
		return head;
	}

	

	@Override
	public String toString() {

		Node<T> newNode = head;
		String s ="";

		while (newNode != null) {
		
			s += newNode.getData() ;
			newNode = newNode.getNext();
		}
		return s;

	}


	public void insertf(T data) {
		Node<T> newNode = new Node<T>(data);
		Node<T> curr = head;
		Node<T> prev = null;
		for (; (curr != null) && curr.getData().compareTo(data) < 0; prev = curr, curr = curr.getNext())
			;

		if (head == null) // case0 add to empty link list
			head = newNode;
		else if (prev == null) { // case1 add to empty first element
			newNode.setNext(head);
			head = newNode;
		} else if (curr == null) { // case2: add the last
			prev.setNext(newNode);
		} else { // case3: insert between two node (curr and prev )
			newNode.setNext(curr);
			prev.setNext(newNode);
		}

	}
	
	
	public void travers() {

		System.out.print("Head->");

		Node<T> newNode = head;

		while (newNode != null) {
			System.out.print(newNode.getData() + "->");
			newNode = newNode.getNext();
		}
		System.out.println("null");

	}
	
	public boolean delete(T x) {
		if (head == null)
			return false;
		if (head.getData().equals(x)) {
			head = head.getNext();
			return true;
		}
		Node<T> previous = head;
		Node<T> current = head.getNext();
		while (current != null) {
			if (current.getData().equals(x)) {
				
				previous.setNext(current.getNext());
				return true;
			}
			previous = current;
			current = current.getNext();
		}
		return false;
	}
	
	
	 public Node<T> searchm(T data){
	        Node<T> n = head;
	        while(n!=null){
	            if(n.getData().compareTo(data) == 0){
	                return n;
	            }
	            else
	                n = n.getNext();
	        }
	      
	        return null;
	    }
	 public T max() {
		 Node<T> curr = head;
		 T max = null;
		 while(curr!= null) {
			 if(curr.getData().compareTo(max) > 0) {
				 max = curr.getData();
			 }
			 curr = curr.getNext();
		 }
		 return max;
	 }
	 
	 
	 public int length() {
		 int i =0 ;
		 Node<T> curr = head;
		 while(curr!=null) {
			 i++;
			 curr= curr.getNext();
		 }
		 return i;
	 }
	 
	 public LinkedList<T> merge(LinkedList<T> L1 , LinkedList<T> L2) {
		 Node<T> N1 = L1.head;
		 Node<T> N2 = L2.head;
		 LinkedList<T> merg = new LinkedList<>();
		 while(N1!=null) {
			 while(N2!=null) {
				 if(N1.getData() != N2.getData()) {
					 merg.insertf(N1.getData());
					 merg.insertf(N2.getData());
				 }
			 }
			 
		 }
		 
		 return merg;
		 
		 
		 
	 }
	public  Node<T> reverseUtil(Node<T> curr, Node<T> prev) {
		 
	        /* If last node mark it head*/
	        if (curr.next == null) {
	            head = curr;
	 
	            /* Update next to prev node */
	            curr.next = prev;
	            return null;
	        }
	 
	        /* Save curr->next node for recursive call */
	        Node<T> next1 = curr.next;
	 
	        /* and update next ..*/
	        curr.next = prev;
	 
	        reverseUtil(next1, curr);
	        return head;
	    }
	 
	    
	 
	 
		

}