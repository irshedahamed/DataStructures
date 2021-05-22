package DataStructure.com.irshed.ds;

import java.util.Iterator;

public class LinkedListImpl implements Iterable<Integer> {
    Node head = null;
    Node tail = null;

    public class Node {
        Integer val;
        Node next;

        public Node(Integer val) {
            this.val = val;
            next = null;
        }
    }

    public void addLast(Integer val) {
        Node newNode = new Node(val);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = tail.next;
        }
    }

    public void addFirst(Integer val) {
        Node newNode = new Node(val);
         if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            Node temp = head;
            head = newNode;
            head.next = temp;
        }
    }

    public void addAfter(Integer val,Integer newVal){
        Node newNode = new Node(newVal);
        Node current = head;
        while(current != null){
            if(current.val == val)
                break;
            current = current.next;
        }
        Node temp = current.next;
        current.next = newNode;
        current.next.next = temp;
        if(temp == null)
            tail = current.next;
    }


    public Node removeFirst() {
       if(head == null || head.next == null)
           return null;
       Node temp = head;
       head = head.next;
       return temp;
    }
    public Node removeLast() {
        if(head == null || head.next == null)
            return null;
       Node prev = null;
       Node current = head;
       while(current.next != null){
           prev = current;
           current = current.next;
       }
       prev.next = null;
       return current;
    }

    public Node remove(Integer val){
        if(head == null) {
            throw new IllegalArgumentException("List is Empty");
        }
        Node current = head;
        Node prev = null;
        while(current!=null){
            if(current.val == val)
                break;
            prev = current;
            current = current.next;
        }
        if(current == head){
            head = current.next;
        }else{
            prev.next = current.next;
        }
        return current;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node trav = head;

            @Override
            public boolean hasNext() {
                return trav != null;
            }

            @Override
            public Integer next() {
                Integer val = trav.val;
                trav = trav.next;
                return val;
            }
        };
    }

    @Override
    public String toString(){
        Node current = head;
        StringBuilder str = new StringBuilder();
        str.append("[");
        while(current.next != null){
            str.append(current.val).append("->");
            current = current.next;
        }
        str.append(current.val).append("]");
        return str.toString();
    }
}
