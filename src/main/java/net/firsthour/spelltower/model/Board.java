package net.firsthour.spelltower.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Board {
	
	private int width;
	private List<Square> squares = new ArrayList<>();
	
	public void remove(Square square) {
		if(square.getIndex() + width >= squares.size()) {
			//top row, clear out the square
			square.setLetter(' ');
			square.setRowClear(false);
			square.setStarred(false);
			return;
		}
		
		Square above = squares.get(square.getIndex() + width);
		square.setLetter(above.getLetter());
		square.setRowClear(above.isRowClear());
		square.setStarred(above.isStarred());
		remove(above);
	}
	
	public boolean isAlmostThere() {
		for(int i = width * 2; i < squares.size(); i++) {
			if(squares.get(i).getLetter() != ' ') {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean isClear() {
		for(var square : squares) {
			if(square.getLetter() != ' ') {
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		var sb = new StringBuilder();
		
		for(int y = squares.size() / width - 1; y >= 0; y--) {
			for(int x = 0; x < width; x++) {
				sb.append(squares.get(y * width + x).getLetter());
		    }
			sb.append(System.lineSeparator());
		}
		
		return sb.toString();
	}
}
