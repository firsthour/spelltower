package net.firsthour.spelltower.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.firsthour.spelltower.util.Arrow;
import net.firsthour.spelltower.util.SquareUtil;

public class Word {
	
	private final List<Square> squares;
	private final Board board;
	private int width;
	private Integer savedScore = null;
	private Set<Integer> indexesCounted = new TreeSet<>();
	
	public Word(List<Square> squares, Board board) {
		this.squares = squares;
		this.board = board;
		this.width = board.getWidth();
	}
	
	public int getScore() {
		if(savedScore != null) {
			return savedScore;
		}
		
		int score = 0;
		int multiplier = 1;
		
		Set<Integer> rowsCleared = new HashSet<>();
		
		for(var square : squares) {
			score += square.getScore();
			multiplier += square.isStarred() ? 1 : 0;
			indexesCounted.add(square.getIndex());
			
			if(square.isRowClear()) {
				rowsCleared.add(square.getIndex() / width);
			}
		}
		
		int bonus = 0;
		
		for(var square : squares) {
			for(var adj : square.getAdjacents()) {
				if(indexesCounted.contains(adj.getIndex())) {
					continue;
				}
				
				//right
				if(adj.getIndex() == square.getIndex() + 1 && (squares.size() >= 5 || adj.getLetter() == ' ')) {
					bonus += adj.getScore();
					indexesCounted.add(adj.getIndex());
				}
				
				//up
				if(adj.getIndex() == square.getIndex() + width && (squares.size() >= 5 || adj.getLetter() == ' ')) {
					bonus += adj.getScore();
					indexesCounted.add(adj.getIndex());
				}
				
				//left
				if(adj.getIndex() == square.getIndex() - 1 && (squares.size() >= 5 || adj.getLetter() == ' ')) {
					bonus += adj.getScore();
					indexesCounted.add(adj.getIndex());
				}
				
				//down
				if(adj.getIndex() == square.getIndex() - width && (squares.size() >= 5 || adj.getLetter() == ' ')) {
					bonus += adj.getScore();
					indexesCounted.add(adj.getIndex());
				}
			}
		}
		
		if(!rowsCleared.isEmpty()) {
			for(int row : rowsCleared) {
				for(var square : board.getSquares()) {
					if(indexesCounted.contains(square.getIndex())) {
						continue;
					}
					
					if(square.getIndex() / width == row) {
						bonus += square.getScore();
						indexesCounted.add(square.getIndex());
					}
				}
			}
		}
		
		savedScore = (score + bonus) * squares.size() * multiplier;
		
		return savedScore;
	}
	
	public void removeFromBoard() {
		//remove in reverse from right to left, top to bottom
		List<Integer> indexes = new ArrayList<>(indexesCounted).reversed();
		
		for(Integer index : indexes) {
			var squareToRemove = board.getSquares().get(index);
			board.remove(squareToRemove);
		}
	}
	
	@Override
	public String toString() {
		int x = squares.get(0).getIndex() % width + 1;
		int y = squares.get(0).getIndex() / width + 1;
		return SquareUtil.getWord(squares) + " (" + getScore() + ") [" + x + ", " + y + "] " + getPath();
	}
	
	private String getPath() {
		String path = "";
		
		for(int i = 1; i < squares.size(); i++) {
			var previous = squares.get(i - 1);
			var current = squares.get(i);
			
			int diff = current.getIndex() - previous.getIndex();
			
			if(diff == 1) {
				path += Arrow.RIGHT;
			} else if(diff == width + 1) {
				path += Arrow.UP_RIGHT;
			} else if(diff == width) {
				path += Arrow.UP;
			} else if(diff == width - 1) {
				path += Arrow.UP_LEFT;
			} else if(diff == -1) {
				path += Arrow.LEFT;
			} else if(diff == -width - 1) {
				path += Arrow.DOWN_LEFT;
			} else if(diff == -width) {
				path += Arrow.DOWN;
			} else if(diff == -width + 1) {
				path += Arrow.DOWN_RIGHT;
			}
			
			path += " ";
		}
		
		return path;
	}
}
