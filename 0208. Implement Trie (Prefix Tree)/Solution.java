/**
 * Implement a trie with insert, search, and startsWith methods.
 * 
 * Idea: 
 * Each node in our Trie, is of type TrieNode as defined in the code.
 * Each node has a marker for endOfWord and saves children in a array, of size 26.
 * 
 * Space Complexity: O(n*k), n strings, k is length of largest string.
 * Time Complexity: Insert, Search and StartsWith: O(k), for each query, where k is length of string.
 * 
 * Leetcode Results:
 * Runtime: 77 ms, faster than 54.69% of Java online submissions for Implement Trie (Prefix Tree).
 * Memory Usage: 51.2 MB, less than 98.08% of Java online submissions for Implement Trie (Prefix Tree).
 */

/**
 * Class TrieNode
 */
class TrieNode {
    // marker for end of word
    boolean isEndOfWord;
    // stores references
    TrieNode[] children;

    /**
     * Constructor
     */
    public TrieNode() {
        children = new TrieNode[26];
    }
}

public class Tries {

    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        // init empty trie
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     * 
     * @param word to insert.
     */
    public void insert(String word) {
        TrieNode cursor = root;
        char c;

        for (int i = 0; i < word.length(); i++) { // iterate
            c = word.charAt(i);
            if (cursor.children[c - 'a'] == null) { // create if null
                cursor.children[c - 'a'] = new TrieNode();
            }
            cursor = cursor.children[c - 'a']; // update cursor
        }
        cursor.isEndOfWord = true; // mark end
    }

    /**
     * Returns if the word is in the trie.
     * 
     * @param word to lookup.
     * @return true if exists, false otherwise.
     */
    public boolean search(String word) {
        TrieNode cursor = root;
        char c;

        for (int i = 0; i < word.length(); i++) { // iterate
            c = word.charAt(i);
            if (cursor.children[c - 'a'] == null) { // miss
                return false;
            }
            cursor = cursor.children[c - 'a']; // update cursor
        }
        return cursor.isEndOfWord;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode cursor = root;
        char c;

        for (int i = 0; i < prefix.length(); i++) { // iterate
            c = prefix.charAt(i);
            if (cursor.children[c - 'a'] == null) { // miss
                return false;
            }
            cursor = cursor.children[c - 'a']; // update cursor
        }
        return true;
    }
}