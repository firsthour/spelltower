package net.firsthour.spelltower.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import net.firsthour.spelltower.util.Score;

@Data
@RequiredArgsConstructor
@ToString(exclude = "adjacents")
public class Square {

	private final int index;
	private char letter;
	private boolean rowClear;
	private boolean starred;
	private List<Square> adjacents = new ArrayList<>();
	
	public int getScore() {
		return Score.find(letter);
	}
}
