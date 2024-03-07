package net.firsthour.spelltower;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import net.firsthour.spelltower.model.Board;
import net.firsthour.spelltower.model.Game;
import net.firsthour.spelltower.util.BoardFactory;
import net.firsthour.spelltower.util.WordMapFactory;
import net.firsthour.spelltower.words.WordMap;

public class SpellTower {
	
	//board defined from left to right, bottom row to top row
	//                                        1        2        3        4        5        6        7        8        9        10       11       12       13       |
	private static final String GAME_BOARD = "iuehairndaej hdfppfontamsricitegwxrnecokdlammisbadnroeborseutvsrosgneqrasziseesnteuaLu ohcbplc etlGuct tyieislpysoaNi";
	private static final int BOARD_WIDTH = 9;
	
	public static void main(String[] args) throws IOException {
		if(GAME_BOARD.length() % BOARD_WIDTH != 0) {
			System.err.println("Invalid board, board length must be divisible by width!");
			return;
		}
		
		boolean simulating = false;
		int simulations = 1;
		if(args.length > 0 && "sim".equals(args[0])) {
			simulating = true;
			
			if(args.length > 1) {
				simulations = Integer.parseInt(args[1]);
				System.out.println("simulating " + simulations);
			}
		}
		
		WordMap wordMap = WordMapFactory.create();
		
		if(!simulating) {
			playSingleGame(wordMap);
			return;
		}
		
		simulate(simulations, wordMap);
	}

	private static void simulate(int simulations, WordMap wordMap) {
		long start = System.currentTimeMillis();
		List<Game> games = Collections.synchronizedList(new ArrayList<>());
		AtomicInteger count = new AtomicInteger();
		
		try(ExecutorService executor = Executors.newFixedThreadPool(20)) {
			for(int i = 0; i < simulations; i++) {
				executor.execute(() -> {
					Board board = BoardFactory.create(BOARD_WIDTH, GAME_BOARD);
					var game = new Game(board, wordMap, true, null);
					game.play();
					games.add(game);
					int current = count.incrementAndGet();
					if(current % 1000 == 0) {
						System.out.println("finished " + current + " games");
					}
				});
			}
		}
		
		long end = System.currentTimeMillis();
		System.out.println("Simulated " + simulations + " in " + (end - start) + " ms");
		
		games.sort(Comparator.comparing(Game::getScore).reversed());
		
		for(int i = 0; i < Math.min(10, games.size()); i++) {
			var game = games.get(i);
			System.out.println();
			System.out.println("Game " + (i + 1) + " = " + game.getScore() + (game.isCleared() ? " (clear)" : game.isAlmostThere() ? " (almost)" : ""));
			for(String selectedWord : game.getSelectedWords()) {
				System.out.println(selectedWord);
			}
		}
	}
	
	public static void playSingleGame(WordMap wordMap) {
		try(Scanner scanner = new Scanner(System.in)) {
			Board board = BoardFactory.create(BOARD_WIDTH, GAME_BOARD);
			var game = new Game(board, wordMap, false, scanner::nextInt);
			game.play();
		}
	}
}
