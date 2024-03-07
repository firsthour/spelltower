package net.firsthour.spelltower.finder;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.firsthour.spelltower.model.Board;
import net.firsthour.spelltower.model.Square;
import net.firsthour.spelltower.model.Word;
import net.firsthour.spelltower.util.SquareUtil;
import net.firsthour.spelltower.words.Contains;
import net.firsthour.spelltower.words.WordMap;

@RequiredArgsConstructor
@Getter
public class WordFinder {
	
	private final Board board;
	private final WordMap words;
	private List<Word> found = new ArrayList<>();
	private int checked = 0;
	
	public void find() {
		for(Square square : board.getSquares()) {
			find(List.of(square));
		}
	}
	
	private void find(List<Square> squares) {
		if(SquareUtil.invalidWord(squares)) {
			return;
		}
		
		String word = SquareUtil.getWord(squares);
		
		checked++;
		
		var contains = words.contains(word);
		
		if(contains == Contains.TRUE || contains == Contains.INCOMPLETE) {
			if(contains == Contains.TRUE) {
				found.add(new Word(squares, board));
			}
			
			for(Square adj : squares.get(squares.size() - 1).getAdjacents()) {
				List<Square> longerSquares = new ArrayList<>(squares);
				longerSquares.add(adj);
				find(longerSquares);
			}
		}
	}
}
