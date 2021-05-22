package DataStructure.com.irshed.ds;

import java.util.Iterator;
import java.util.LinkedList;

class Node {
    int hash;
    String key;
    String value;

    public Node(String key, String value) {
        this.key = key;
        this.value = value;
        this.hash = key.hashCode();
    }

}

public class HashingSeperateChaining {
    int size = 0;
    int capacity = 3;
    double threshold = 0.75;

    double maxCapacity = threshold * capacity;

    LinkedList[] hashTable = new LinkedList[capacity];

    public String get(String key) {
        Node temp = getNode(key);
        if (temp != null)
            return temp.value;
        return null;
    }

    private Node getNode(String key) {
        int keyHash = normalizedHash(key);
        LinkedList values = hashTable[keyHash];
        if (values != null) {
            Iterator<Node> itr = values.iterator();
            while (itr.hasNext()) {
                Node temp = itr.next();
                if (temp.key.equals(key))
                    return temp;
            }
        }
        return null;
    }

    public void removeKey(String key) {
        Node temp = getNode(key);
        int keyHash = normalizedHash(key);
        LinkedList values = hashTable[keyHash];
        if (values != null && temp != null)
            values.remove(temp);
    }

    public int normalizedHash(String key) {
        int hashCode = key.hashCode();
        return (hashCode & 0x7FFFFFFF) % capacity;
    }

    public void insert(String key, String value) {
        if (size >= maxCapacity) {
            reSizeHashTable();
        }
        int keyPos = normalizedHash(key);
        Node node = new Node(key, value);
        LinkedList temp = hashTable[keyPos];
        if (temp == null) {
            temp = new LinkedList<>();
        }
        temp.push(node);
        hashTable[keyPos] = temp;
        size++;
    }

    private void reSizeHashTable() {
        int oldCapacity = capacity;
        capacity *= 2;
        LinkedList[] newTable = new LinkedList[capacity];
        for (int i = 0; i < oldCapacity; i++) {
            LinkedList old = hashTable[i];
            if (old != null) {
                Iterator<Node> itr = old.iterator();
                while (itr.hasNext()) {
                    Node temp = itr.next();
                    int newKeyPos = normalizedHash(temp.key);
                    LinkedList newList = newTable[newKeyPos];
                    if (newList == null)
                        newList = new LinkedList<>();
                    newList.push(temp);
                    newTable[newKeyPos] = newList;
                }
            }
        }
        maxCapacity = threshold * capacity;
        this.hashTable = newTable;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            LinkedList temp = hashTable[i];
            if (temp != null) {
                Iterator<Node> itr = temp.iterator();
                while (itr.hasNext()) {
                    Node node = itr.next();
                    str.append(node.key + "-" + node.value + "\n");
                }
            }
        }
        return str.toString();
    }

}
