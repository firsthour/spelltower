package net.firsthour.spelltower.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;

import net.firsthour.spelltower.model.Square;

public class SquareUtil {
	
	private SquareUtil() {}
	
	public static String getWord(List<Square> squares) {
		return squares.stream().map(Square::getLetter)
			.collect(Collector.of(
				StringBuilder::new,
				StringBuilder::append,
				StringBuilder::append,
				StringBuilder::toString));
	}
	
	public static boolean invalidWord(List<Square> squares) {
		Set<Integer> indexes = new HashSet<>();
		
		for(var square : squares) {
			if(square.getLetter() == ' ') {
				return true;
			}
			
			boolean unique = indexes.add(square.getIndex());
			
			if(!unique) {
				return true;
			}
		}
		
		return false;
	}
}
