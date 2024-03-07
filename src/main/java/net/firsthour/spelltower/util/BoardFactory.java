package net.firsthour.spelltower.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.firsthour.spelltower.model.Board;
import net.firsthour.spelltower.model.Square;

public class BoardFactory {
	
	private static final Set<Character> ROW_CLEAR_CHARS = Set.of('j', 'q', 'x', 'z');
	
	private BoardFactory() {}
	
	public static Board create(int width, String leftRightBottomTop) {
		var board = new Board();
		board.setWidth(width);
		
		List<Square> squares = initializeSquares(leftRightBottomTop);
		
		hookupAdjacents(squares, width);
		
		board.setSquares(squares);
		
		return board;
	}

	private static List<Square> initializeSquares(String leftRightBottomTop) {
		List<Square> squares = new ArrayList<>();
		
		int i = 0;
		for(char c : leftRightBottomTop.toCharArray()) {
			var square = new Square(i++);
			
			square.setLetter(Character.toUpperCase(c));
			
			if(Character.isUpperCase(c)) {
				square.setStarred(true);
			}
			
			if(ROW_CLEAR_CHARS.contains(c)) {
				square.setRowClear(true);
			}
			
			squares.add(square);
		}
		
		return squares;
	}
	
	private static void hookupAdjacents(List<Square> squares, int width) {
		for(int i = 0; i < squares.size(); i++) {
			List<Square> adj = squares.get(i).getAdjacents();
			
			//right
			if((i + 1) % width != 0) {
				adj.add(squares.get(i + 1));
				printRelationship(squares, " r", i, i + 1);
			}
			
			//upper right
			if((i + 1) % width != 0 && (i + width) < squares.size()) {
				adj.add(squares.get(i + 1 + width));
				printRelationship(squares, "ur", i, i + 1 + width);
			}
			
			//up
			if((i + width) < squares.size()) {
				adj.add(squares.get(i + width));
				printRelationship(squares, " u", i, i + width);
			}
			
			//upper left
			if(i % width != 0 && (i + width) < squares.size()) {
				adj.add(squares.get(i - 1 + width));
				printRelationship(squares, "ul", i, i - 1 + width);
			}
			
			//left
			if(i % width != 0) {
				adj.add(squares.get(i - 1));
				printRelationship(squares, " l", i, i - 1);
			}
			
			//down left
			if(i % width != 0 && i - width >= 0) {
				adj.add(squares.get(i - 1 - width));
				printRelationship(squares, "dl", i, i - 1 - width);
			}
			
			//down
			if(i - width >= 0) {
				adj.add(squares.get(i - width));
				printRelationship(squares, " d", i, i - width);
			}
			
			//down right
			if((i + 1) % width != 0 && i - width >= 0) {
				adj.add(squares.get(i + 1 - width));
				printRelationship(squares, "dr", i, i + 1 - width);
			}
		}
	}
	
	private static void printRelationship(List<Square> squares, String dir, int i1, int i2) {
		//System.out.println(dir + " = " + squares.get(i1).getLetter() + " -> " + squares.get(i2).getLetter());
	}
}
