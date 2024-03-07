package net.firsthour.spelltower.words;

import java.util.Map;
import java.util.TreeMap;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Node {
	
	private final char character;
	private Map<Character, Node> children = new TreeMap<>();
	private boolean end;
}
