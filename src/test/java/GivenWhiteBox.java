import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GivenWhiteBox {
    Game game;

    @Before
    public void setUp() throws Exception {
        game = new Game("lion", "TestPlayer");
    }

    /**
     * Test sequence for Node Coverage: Enter -> Node 1 -> Node 8 -> Node 9
     */
    @Test
    public void testCountCorrectLettersEmptyGuesses() {
        game.guesses.clear();
        int result = game.countCorrectLetters();
        assertEquals(0, result);
    }

    /**
     * Test sequence for Node Coverage: Enter -> Node 1 -> Node 2 -> Node 3 -> Node 4 -> Node 5 -> Node 7 -> Node 9
     */
    @Test
    public void testCountCorrectLettersAllCorrect() {
        game.guesses.clear();
        game.guesses.add("l");
        game.guesses.add("i");
        game.guesses.add("o");
        game.guesses.add("n");
        int result = game.countCorrectLetters();
        assertEquals(4, result); // Update if necessary
    }

    /**
     * Test sequence for Node Coverage: Enter -> Node 1 -> Node 2 -> Node 3 -> Node 4 -> Node 6 -> Node 7 -> Node 9
     */
    @Test
    public void testCountCorrectLettersAllIncorrect() {
        game.guesses.clear();
        game.guesses.add("a");
        game.guesses.add("b");
        game.guesses.add("c");
        int result = game.countCorrectLetters();
        assertEquals(0, result);
    }

    /**
     * Test sequence for Edge Coverage: Enter -> Node 1 -> Node 8 -> Node 9
     */
    @Test
    public void testCountCorrectLettersEdge1() {
        game.guesses.clear();
        int result = game.countCorrectLetters();
        assertEquals(0, result);
    }

    /**
     * Test sequence for Edge Coverage: Enter -> Node 1 -> Node 2 -> Node 3 -> Node 4 -> Node 5 -> Node 3 (loop back) -> Node 7 -> Node 9
     */
    @Test
    public void testCountCorrectLettersEdge2() {
        game.guesses.clear();
        game.guesses.add("l");
        game.guesses.add("i");
        game.guesses.add("o");
        game.guesses.add("n");
        int result = game.countCorrectLetters();
        assertEquals(4, result); // Update if necessary
    }

    /**
     * Test sequence for Edge Coverage: Enter -> Node 1 -> Node 2 -> Node 3 -> Node 4 -> Node 6 -> Node 3 (loop back) -> Node 7 -> Node 9
     */
    @Test
    public void testCountCorrectLettersEdge3() {
        game.guesses.clear();
        game.guesses.add("a");
        game.guesses.add("b");
        game.guesses.add("c");
        int result = game.countCorrectLetters();
        assertEquals(0, result);
    }

    // Test for countLetters() method
    @Test
    public void testCountLetters() {
        int count = game.countLetters('l');
        assertEquals(1, count);
    }

    // Test for initGame() method
    @Test
    public void testInitGame() {
        game.initGame("tiger", "NewPlayer");
        assertEquals("NewPlayer", game.getName());
        assertEquals("tiger", game.getAnswer());
        assertEquals(10, game.getPoints());
        assertTrue(game.guesses.isEmpty());
        assertTrue(game.answers.isEmpty());
        assertEquals(0, game.getGameStatus());
    }

    // Test for startGame()
    @Test
    public void startGame() {
        Game game = new Game("lion", "Dr. M");
        assertEquals(10, game.getPoints());
    }

    // Additional tests for makeGuess() method
    @Test
    public void testMakeGuessCorrectWord() {
        game.initGame("lion", "Player");
        double response = game.makeGuess("lion");
        assertEquals(0.0, response, 0.0);
        assertEquals(14, game.getPoints());
        assertEquals(1, game.getGameStatus());
    }

    @Test
    public void testMakeGuessIncorrectLetter() {
        game.initGame("lion", "Player");
        double response = game.makeGuess("x");
        assertEquals(1.0, response, 0.0);
        assertEquals(9, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }

    @Test
    public void testMakeGuessCorrectLetter() {
        game.initGame("lion", "Player");
        double response = game.makeGuess("l");
        assertEquals(1.1, response, 0.0);
        assertEquals(11, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }

    @Test
    public void testMakeGuessAlreadyUsed() {
        game.initGame("lion", "Player");
        game.makeGuess("l");
        double response = game.makeGuess("l");
        assertEquals(4.0, response, 0.0);
        assertEquals(9, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }

    @Test
    public void testMakeGuessWithSymbols() {
        game.initGame("lion", "Player");
        double response = game.makeGuess("l!on");
        assertEquals(4.1, response, 0.0);
        assertEquals(7, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }

    @Test
    public void testMakeGuessTooLong() {
        game.initGame("lion", "Player");
        double response = game.makeGuess("elephant");
        assertEquals(2.1, response, 0.0);
        assertTrue(game.getPoints() < 10);
        assertEquals(0, game.getGameStatus());
    }

    @Test
    public void testMakeGuessTooShort() {
        game.initGame("lion", "Player");
        double response = game.makeGuess("li");
        assertEquals(2.2, response, 0.0);
        assertTrue(game.getPoints() < 10);
        assertEquals(0, game.getGameStatus());
    }

    @Test
    public void testMakeGuessAfterGameOver() {
        game.initGame("lion", "Player");
        for (int i = 0; i < 10; i++) {
            game.makeGuess("x" + i);
        }
        double response = game.makeGuess("lion");
        assertEquals(5.0, response, 0.0);
        assertEquals(2, game.getGameStatus());
    }

    @Test
    public void testMakeGuessAfterWin() {
        game.initGame("lion", "Player");
        game.makeGuess("lion");
        double response = game.makeGuess("lion");
        assertEquals(5.1, response, 0.0);
        assertEquals(1, game.getGameStatus());
    }
}
