package org.example;

import java.util.HashMap;
import java.util.Map;

// TODO: task is not completed
class MagicDictionary {
    TrieNode root;

    public MagicDictionary() {
        root = new TrieNode();
        root.dict = new HashMap<>();
    }

    public void buildDict(String[] dictionary) {
        for (int i = 0; i < dictionary.length; i++) {
            TrieNode node = root;
            for (int j = 0; j < dictionary[i].length(); j++) {
                Character letter = dictionary[i].charAt(j);
                if (!node.dict.containsKey(letter)) {
                    node.dict.put(letter, new TrieNode(letter));
                }
                node = node.dict.get(letter);
            }
        }
    }

    public boolean search(String searchWord) {
        TrieNode node = root;
        boolean mismatchHappened = false;
        for (int i = 0; i < searchWord.length(); i++) {
            Character letter = searchWord.charAt(i);
            if (!node.dict.containsKey(letter) && mismatchHappened) {
                return false;
            }

            if (!node.dict.containsKey(letter)) {
                mismatchHappened = true;
            }
        }

        return true;
    }

}

class TrieNode {
    Character item;
    Map<Character, TrieNode> dict;
    boolean isEndOfWord;

    public TrieNode() {
        dict = new HashMap<>();
        isEndOfWord = false;
    }

    public TrieNode(Character item) {
        this.item = item;
        dict = new HashMap<>();
        isEndOfWord = true;
    }

    public void addLetter(Character letter) {
        if (!dict.containsKey(letter)) {
            dict.put(letter, new TrieNode(letter));
        }
    }
}
