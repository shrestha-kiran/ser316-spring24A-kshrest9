import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class guessTest {

    private Game game;

    @Before
    public void setUp() {
        game = new Game("lion", "TestPlayer");
    }

    @Test
    public void testCorrectGuess() {
        double response = game.makeGuess("lion");
        assertEquals(0.0, response, 0.0);
        assertEquals(14, game.getPoints());
        assertEquals(1, game.getGameStatus());
    }

    @Test
    public void testIncorrectLetterGuess() {
        double response = game.makeGuess("x");
        assertEquals(1.0, response, 0.0);
        assertEquals(9, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }

    @Test
    public void testCorrectLetterGuess() {
        double response = game.makeGuess("l");
        assertEquals(1.1, response, 0.0);
        assertEquals(11, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }

    @Test
    public void testGuessAlreadyUsed() {
        game.makeGuess("l");
        double response = game.makeGuess("l");
        assertEquals(4.0, response, 0.0);
        assertEquals(9, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }

    @Test
    public void testGuessWithSymbols() {
        double response = game.makeGuess("l!on");
        assertEquals(4.1, response, 0.0);
        assertEquals(7, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }

    @Test
    public void testGameOverAfterTenGuesses() {
        for (int i = 0; i < 10; i++) {
            game.makeGuess("x" + i);
        }
        double response = game.makeGuess("lion");
        assertEquals(5.0, response, 0.0);  // Ensure this checks for game over after 10 guesses
        assertEquals(2, game.getGameStatus());  // Check game status is game over
    }


    @Test
    public void testIncorrectGuessTooLong() {
        double response = game.makeGuess("elephant");
        assertEquals(2.1, response, 0.0);
        assertTrue(game.getPoints() < 10);
        assertEquals(0, game.getGameStatus());
    }
}
