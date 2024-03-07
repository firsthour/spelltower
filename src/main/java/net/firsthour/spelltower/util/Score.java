package net.firsthour.spelltower.util;

public enum Score {
	
	A(1),
	B(4),
	C(4),
	D(3),
	E(1),
	F(5),
	G(3),
	H(5),
	I(1),
	J(9),
	K(6),
	L(2),
	M(4),
	N(2),
	O(1),
	P(4),
	Q(12),
	R(2),
	S(1),
	T(2),
	U(1),
	V(5),
	W(5),
	X(9),
	Y(5),
	Z(11);
	
	private int value;
	
	private Score(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public static int find(char c) {
		for(Score score : values()) {
			if(score.name().charAt(0) == c) {
				return score.value;
			}
		}
		
		return 0;
	}
}
