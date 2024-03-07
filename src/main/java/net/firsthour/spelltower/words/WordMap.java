package net.firsthour.spelltower.words;

import java.util.Map;
import java.util.TreeMap;

public class WordMap {
	
	private Map<Character, Node> startingLetters = new TreeMap<>();
	private int size = 0;
	
	public void add(String word) {
		if(word.length() < 3) {
			return;
		}
		
		word = word.toUpperCase();
		
		char character = word.charAt(0);
		
		add(word.substring(1), startingLetters.computeIfAbsent(character, c -> new Node(character)));
	}
	
	public Contains contains(String word) {
		word = word.toUpperCase();
		
		char character = word.charAt(0);
		
		return contains(word.substring(1), startingLetters.get(character));
	}
	
	private void add(String segment, Node node) {
		if(segment.isEmpty()) {
			node.setEnd(true);
			size++;
			return;
		}
		
		char character = segment.charAt(0);
		
		add(segment.substring(1), node.getChildren().computeIfAbsent(character, c -> new Node(character)));
	}
	
	private Contains contains(String segment, Node node) {
		if(node == null) {
			return Contains.FALSE;
		}
		
		if(segment.isEmpty()) {
			if(node.isEnd()) {
				return Contains.TRUE;
			} else {
				return Contains.INCOMPLETE;
			}
		}
		
		return contains(segment.substring(1), node.getChildren().get(segment.charAt(0)));
	}
	
	public int getSize() {
		return size;
	}
}
