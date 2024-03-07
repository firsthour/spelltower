package net.firsthour.spelltower.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import net.firsthour.spelltower.words.WordMap;

public class WordMapFactory {
	
	private WordMapFactory() {}
	
	public static WordMap create() throws IOException {
		var map = new WordMap();
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(WordMapFactory.class.getResourceAsStream("/wordlist.txt")))) {
			String line;
			while((line = br.readLine()) != null) {
				map.add(line);
			}
		}
		
		return map;
	}
}
