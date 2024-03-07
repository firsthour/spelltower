package net.firsthour.spelltower.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.IntSupplier;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.firsthour.spelltower.finder.WordFinder;
import net.firsthour.spelltower.words.WordMap;

@Data
@RequiredArgsConstructor
public class Game {
	
	private final Board board;
	private final WordMap wordMap;
	private final boolean simulating;
	private final IntSupplier userInput;
	
	private int score = 0;
	private boolean almostThere = false;
	private boolean cleared = false;
	private List<String> selectedWords = new ArrayList<>();
	
	Random random = new Random();
	
	public void play() {
		while(true) {
			log(board);
			
			var finder = new WordFinder(board, wordMap);
			finder.find();
			
			List<Word> foundWords = finder.getFound();
			Collections.sort(foundWords, Comparator.comparing(Word::getScore));
			
			for(int i = 0; i < foundWords.size(); i++) {
				log(i + ". " + foundWords.get(i));
			}
			
			if(foundWords.isEmpty()) {
				log("No found words");
				
				if(board.isAlmostThere()) {
					log("Almost there! +1000");
					score += 1000;
					almostThere = true;
				}
				
				if(board.isClear()) {
					log("Cleared! +1000");
					score += 1000;
					cleared = true;
				}
				
				break;
			}
			
			Word selectedWord = null;
			
			while(selectedWord == null) {
				int input = -1;
				
				if(simulating) {
					int searchSize = 10;
					if(foundWords.size() < searchSize) {
						input = random.nextInt(0, foundWords.size());
					} else {
						input = random.nextInt(foundWords.size() - searchSize, foundWords.size());
					}
				} else {
					System.out.println();
					System.out.print("Pick a word: ");
					input = userInput.getAsInt();
				}
				
				if(input >= 0 && input < foundWords.size()) {
					selectedWord = foundWords.get(input);
				}
			}
			
			log("selected = " + selectedWord);
			score += selectedWord.getScore();
			selectedWords.add(selectedWord.toString());
			selectedWord.removeFromBoard();
		}
		
		log("Final Score = " + score);
		
		log("Words:");
		for(String selectedWord : selectedWords) {
			log(selectedWord);
		}
	}
	
	private void log(Object object) {
		if(simulating) {
			return;
		}
		
		System.out.println(object.toString());
	}
}
