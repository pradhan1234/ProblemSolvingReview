/**
 * Idea: To use HashMap and DoublyLinkedList.
 * Another Approach: Use LinkedHashMap structure.
 * 
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, 
 * it should invalidate the least recently used item before inserting a new item.
 * 
 * The cache is initialized with a positive capacity.
 */

import java.util.Map;
import java.util.HashMap;
import java.lang.Integer;

// DoublyLinkedList Node
class Node {
    int key, value;
    Node prev, next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

public class LRUCache {

    int capacity;
    Map<Integer, Node> map;
    Node head, tail;

    // constructor
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node cursor = map.get(key);
        if (cursor != null) {
            remove(cursor);
            insertFirst(cursor);
            return cursor.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        Node cursor = map.get(key);
        if (cursor != null) {
            remove(cursor);
        }

        cursor = new Node(key, value);
        insertFirst(cursor);
        map.put(key, cursor);

        if (map.size() > capacity) {
            cursor = tail.prev;
            remove(cursor);
            map.remove(cursor.key);
        }
    }

    // helper methods
    public void insertFirst(Node cursor) {
        Node first = head.next;
        cursor.prev = head;
        cursor.next = first;
        first.prev = cursor;
        head.next = cursor;
    }

    public void remove(Node cursor) {
        Node prevNode = cursor.prev;
        Node nextNode = cursor.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }
}
