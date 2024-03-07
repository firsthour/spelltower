package net.firsthour.spelltower.util;

public enum Arrow {
	
	RIGHT('\u21AA'),
	UP_RIGHT('\u2197'),
	UP('\u2B06'),
	UP_LEFT('\u2196'),
	LEFT('\u21A9'),
	DOWN_LEFT('\u2199'),
	DOWN('\u2B07'),
	DOWN_RIGHT('\u2198');
	
	private char unicode;
	
	private Arrow(char unicode) {
		this.unicode = unicode;
	}
	
	@Override
	public String toString() {
		return String.valueOf(unicode);
	}
}
