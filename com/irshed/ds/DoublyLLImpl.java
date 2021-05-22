package DataStructure.com.irshed.ds;

public class DoublyLLImpl {

    Node head = null;
    Node tail = null;

    class Node {
        Integer val;
        Node prev;
        Node next;

        public Node(Integer val) {
            this.val = val;
            prev = null;
            next = null;
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
            newNode.next = temp;
            temp.prev = newNode;
        }
    }

    public void addLast(Integer val) {
        Node newNode = new Node(val);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            Node temp = tail;
            tail = newNode;
            temp.next = newNode;
            newNode.prev = temp;
        }
    }

    public void addAfter(Integer val, Integer newVal) {
        int flag = 0;
        Node newNode = new Node(newVal);
        Node current = head;
        while (current != null) {
            if (current.val == val) {
                flag = 1;
                break;
            }
            current = current.next;
        }

        if (flag == 1) {
            Node temp = current.next;
            current.next = newNode;
            newNode.prev = current;
            newNode.next = temp;
            if (temp != null)
                temp.prev = newNode;
            else
                tail = newNode;
        } else {
            throw new IllegalArgumentException("No Data Found");
        }
    }

    //Remove handling Need to be Done
    public Node removeFirst() {
        if (head == null || head.next == null)
            return null;
        Node temp = head.next;
        temp.prev = null;
        head = temp;
        return head;
    }

    public Node removeLast() {
        if (head == null || head.next == null)
            return null;
        tail = tail.prev;
        tail.next = null;
        return tail;
    }

    public Node remove(Integer val) {
        int flag = 0;
        Node current = head;
        while (current != null) {
            if (current.val == val) {
                flag = 1;
                break;
            }
            current = current.next;
        }

        if(current == head){
            return removeFirst();
        }
        if(current == tail){
            return removeLast();
        }

        if (flag == 1) {
            Node temp = current.next;
            Node previous = current.prev;
            previous.next = temp;
            temp.prev = previous;
        }
        return current;
    }


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Node current = head;
        str.append("[");
        while (current.next != null) {
            str.append(current.val).append("->");
            current = current.next;
        }
        str.append(current.val).append("]");
        return str.toString();
    }


}
