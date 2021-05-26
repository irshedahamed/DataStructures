package Algorithm.com.irshed.algo;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Trie {

    private static Node root;

    public Trie() {
        root = new Node();
    }

    private static class Node {
        Map<Character, Node> map = new HashMap<>();
        boolean wordEnd = false;
        int count = 0;
    }

    public static void insert(String str) {
        Node curr = root;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            Map<Character, Node> charMap = curr.map;
            Node chNode;
            if (charMap.containsKey(ch)) {
                chNode = charMap.get(ch);
                chNode.count += 1;
            } else {
                chNode = new Node();
                chNode.count = 1;
            }
            if (i == str.length() - 1) {
                chNode.wordEnd = true;
            }
            charMap.put(ch, chNode);
            curr = chNode;
        }
    }

    public static boolean search(String str) {
        Node curr = iterateToLast(str);
        if (curr != null) {
            return curr.wordEnd;
        }
        return false;
    }

    public static int prefixSum(String str) {
        Node curr = iterateToLast(str);
        if (curr != null) {
            return curr.count;
        }
        return 0;
    }

    private static Node iterateToLast(String str) {
        Node curr = root;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            Map<Character, Node> charMap = curr.map;
            if (!charMap.containsKey(ch))
                return null;
            else {
                Node next = charMap.get(ch);
                curr = next;
            }
        }
        return curr;
    }

    public static boolean delete(String str) {
        if (!search(str)) {
            return false;
        }
        Node curr = root;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            Map<Character, Node> map = curr.map;
            Node node = map.get(ch);
            node.count -= 1;
            map.put(ch, node);
            curr = node;
        }
        curr.wordEnd = false;
        
        return true;
    }


    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("shopping");
        trie.insert("shopper");
        trie.insert("for");
        trie.insert("formal");
        trie.insert("forrier");
        trie.insert("fortitude");
        trie.insert("forty");
        trie.insert("zebra");
        trie.insert("zeb");
        System.out.println(trie.search("zebra"));
        System.out.println(trie.delete("zebra"));
        System.out.println(trie.search("zebra"));
        System.out.println(trie.search("zeb"));


        System.out.println(trie.prefixSum("for"));
        System.out.println(trie.search("formal"));

        System.out.println(trie.search("z"));
        System.out.println(trie.search("xerox"));
        System.out.println(trie.search("zebra"));
    }


}
