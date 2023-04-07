package org.example;

public class MyHashTable {
    private Node[] table;
    private int size;
    public MyHashTable(int size) {
        this.size = size;
        table = new Node[size];
    }

    //Add a key-value pair to the hash table
    public void insert(String key, String value) {
        int index = computeHash(key);
        Node selectedNode = table[index];

       //
        while (selectedNode != null) {
            //checkimg if the key exists and if exists it updates it
            if (selectedNode.key.equals(key)) {
                selectedNode.value = value;
                return;
            }
            selectedNode = selectedNode.next;
        }

        // If the key doesnt exists it adds it to the table
        Node newNode = new Node(key, value);
        newNode.next = table[index];
        table[index] = newNode;
    }
    public String get(String key) {
        int index = computeHash(key);
        Node selectedNode = table[index];

        //goes through and searches for the key in th list
        while (selectedNode != null) {
            if (selectedNode.key.equals(key)) {
                return selectedNode.value;
            }
            selectedNode = selectedNode.next;
        }

        return null; //if it cant find the key
    }

    //Processing, checking if its positive int
    private int computeHash(String key) {
        return Math.abs(key.hashCode()) % size;
    }

    //store key-value in the table
    class Node {
        String key;
        String value;
        Node next;

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
