`spelltower` is an implemenation of Puzzmo's great puzzle game SpellTower found at https://www.puzzmo.com/play/spelltower

Wordlist from https://github.com/wordnik/wordlist

The SpellTower board can be defined in `SpellTower.java`. Letters are written out in lowercase from left to right, then bottom to top. Starred letters that multiply the score should be capitalized.

For a sample 9x13 board, its definition would look like:

```
private static final String GAME_BOARD = "gevpsnricdrt ieimoyepohuau taanglaskOstgcmpiredidborlefasiseeslroixbnucizserdatirheoisyc chefenmthlSjlsqtwnnuea pAtno";
private static final int BOARD_WIDTH = 9;
```

Notice how there are no line breaks, empty squares are spaces, pinkish row clear letters are not distinguished, and starred letters are in uppercase.

It's meant to be console interactive at this point. Launching `SpellTower.java` without any arguments starts an interactive game that prints the board and then suggests every single word available, ordered by its score ascending. A coordinate starting position is also given (bottom-left corner is [1, 1]) and some basic arrows are provided with the letter route.

```
UEA PATNO
SJLSQTWNN
HEFENMTHL
HEOISYC C
ZSERDATIR
ROIXBNUCI
FASISEESL
EDIDBORLE
OSTGCMPIR
TAANGLASK
YEPOHUAU 
DRT IEIMO
GEVPSNRIC

0. SEI (9) [5, 1] ↗ ↪ 
1. SEA (9) [5, 1] ↗ ↗ 
2. SEI (9) [5, 1] ↗ ↩
...
3709. FROSHES (728) [1, 7] ⬆ ↪ ⬆ ↖ ↗ ↖ 
3710. ANTIDOTED (810) [3, 4] ↪ ↖ ⬆ ↩ ↙ ⬇ ↘ ↙ 
3711. FORESEES (992) [3, 11] ⬇ ↘ ↩ ↩ ⬆ ⬆ ↖ 

Pick a word: 
```

At this point, you can select a word by entering its corresponding number. The board will be recalculated and the next set of words will be offered.

```
Pick a word: 3706
selected = SELFIES (686) [1, 12] ↗ ↘ ⬇ ↘ ↙ ↩ 
     ATNO
    PTWNN
    QMTHL
    NYC C
H   DATIR
R  XBNUCI
FASISEESL
EDIDBORLE
OSTGCMPIR
TAANGLASK
YEPOHUAU 
DRT IEIMO
GEVPSNRIC

0. SEI (9) [5, 1] ↗ ↪ 
1. SEA (9) [5, 1] ↗ ↗ 
2. SEI (9) [5, 1] ↗ ↩
...
```

When there are no more words available, the game will notify you and present your final score:

```
Final Score = 2442
Words:
SELFIES (686) [1, 12] ↗ ↘ ⬇ ↘ ↙ ↩ 
PATTY (490) [5, 12] ↗ ⬇ ↘ ↙ 
REH (24) [7, 1] ↖ ↖ 
ISOPIESTIC (640) [4, 6] ↪ ↘ ↘ ↗ ↖ ↗ ↖ ↗ ↗ 
SOAPER (384) [2, 5] ↩ ↘ ↘ ↩ ⬇ 
MALIGN (162) [8, 2] ↩ ↖ ↙ ⬆ ↗ 
GASP (36) [1, 1] ↪ ↗ ↘ 
ONIE (20) [9, 6] ⬇ ⬇ ⬇
```

1,000 bonus points are also rewarded each for almost clearing the board (only two rows left) and a full clear. Clearing every letter will net you 2,000 total bonus points.

Using a pink letter in a word will clear out the entire word.

Using five or more letters in a word will also clear out directly adjacent letters.

Scoring follows the normal SpellTower rules.

A simulation mode is also available by using the arguments "sim 50" where the number is how many games you would like to simulate.

When the simulations are complete it displays the ten best scoring games it found.

It is very simple right now and just randomly picks between the top words available without any foresight or look-ahead. There is no way for `spelltower` at this point to manipulate the board to create incredibly high scoring words. It can find full clears with enough simulations.

`spelltower` was created to challenge myself in recreating Puzzmo's great game. It was not meant to game the leaderboard though while developing it I was quite successful.

Future:
- load board from image or website URL
- interface for different solving techniques
- board generation
- non-console UI
