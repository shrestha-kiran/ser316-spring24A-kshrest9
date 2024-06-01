import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BlackBoxGiven {
    private Class<Game> classUnderTest;

    @SuppressWarnings("unchecked")
    public BlackBoxGiven(Object classUnderTest) {
        this.classUnderTest = (Class<Game>) classUnderTest;
    }

    // Define all classes to be tested
    @Parameterized.Parameters
    public static Collection<Object[]> cartClassUnderTest() {
        Object[][] classes = {
            {Game0.class},
            {Game1.class},
            {Game2.class},
            {Game3.class},
            {Game4.class}
        };
        return Arrays.asList(classes);
    }

    private Game createGame() throws Exception {
        Constructor<Game> constructor = classUnderTest.getConstructor();
        return constructor.newInstance();
    }

    Game game;

    @org.junit.Before
    public void setUp() throws Exception {
        game = createGame();
    }


    @Test
    public void winning() {
        game.initGame("lion", "Dr. M");

        double response = game.makeGuess("lion");
        assertEquals(0.0, response, 0.0);
        assertEquals(14, game.getPoints());
        assertEquals(1, game.getGameStatus());
    }

    // EP: Correct Guess (CG)
    @Test
    public void testCorrectGuess() {
        double response = game.makeGuess("lion");
        assertEquals(0.0, response, 0.0);
        assertEquals(14, game.getPoints());
        assertEquals(1, game.getGameStatus());
    }

    // EP: Incorrect Guess (IG)
    @Test
    public void testIncorrectGuess() {
        double response = game.makeGuess("tiger");
        // Should return 2.1 for incorrect guess longer than answer
        assertEquals(2.1, response, 0.0);
        // Points should decrease
        assertEquals(-1, game.getPoints());
    }

    // EP: Repeated Guess (RG)
    @Test
    public void testRepeatedGuess() {
        game.makeGuess("tiger");
        double response = game.makeGuess("tiger");
        assertEquals(4.0, response, 0.0);
        assertEquals(-2, game.getPoints());
    }

    // EP: Non-Alphabetical Guess (NAG)
    @Test
    public void testNonAlphabeticalGuess() {
        double response = game.makeGuess("1234");
        assertEquals(4.1, response, 0.0);
        assertEquals(-3, game.getPoints());
    }

    // EP: Single Character Correct Guess (SCG)
    @Test
    public void testSingleCharacterCorrectGuess() {
        double response = game.makeGuess("l");
        // Considering variations in responses
        assertTrue(response == 1.1 || response == 1.0);
        if (response == 1.1) {
            assertEquals(1, game.getPoints());
        } else {
            assertEquals(0, game.getPoints());
        }
    }

    // EP: Single Character Incorrect Guess (IG, SCG)
    @Test
    public void testSingleCharacterIncorrectGuess() {
        double response = game.makeGuess("x");
        assertEquals(1.0, response, 0.0);
        assertEquals(0, game.getPoints());
    }


    // EP: Exact Length Correct Guess (ELG)
    @Test
    public void testExactLengthCorrectGuess() {
        double response = game.makeGuess("lion");
        assertEquals(0.0, response, 0.0);
        assertEquals(14, game.getPoints());
        assertEquals(1, game.getGameStatus());
    }

    // EP: Less Than Answer Length (LTAL)
    @Test
    public void testLessThanAnswerLength() {
        double response = game.makeGuess("lio");
        assertEquals(2.2, response, 0.0);
        // Should decrease by (guess length - answer length)
        assertTrue(game.getPoints() <= 13);
    }

    // EP: More Than Answer Length (MTAL)
    @Test
    public void testMoreThanAnswerLength() {
        double response = game.makeGuess("lions");
        assertEquals(2.1, response, 0.0);
        // Should decrease by (guess length - answer length)
        assertTrue(game.getPoints() <= 14);
    }

    // BVA: Guess At Limit
    @Test
    public void testGuessAtLimit() {
        for (int i = 0; i < 10; i++) {
            game.makeGuess("a");
        }
        assertTrue(game.getGameStatus() == 2 || game.getGameStatus() == 1);
    }

    // BVA: Exceed Guess Limit
    @Test
    public void testExceedGuessLimit() {
        for (int i = 0; i < 10; i++) {
            game.makeGuess("a");
        }
        double response = game.makeGuess("a");
        // Should return 5.1 if game already over
        assertEquals(5.1, response, 0.0);
        assertTrue(game.getGameStatus() == 1 || game.getGameStatus() == 2);
    }

    // Easter Egg Test Cases
    // Check for Easter Egg output (if present, otherwise ignore)
    @Test
    public void testEasterEgg2() {
        for (int i = 0; i < 10; i++) {
            game.makeGuess("wrong");
        }
    }

    // Check for Easter Egg output (if present, otherwise ignore)
    @Test
    public void testEasterEgg3() {
        game.makeGuess("tiger");
        game.makeGuess("tiger");
    }

    // Check for Easter Egg output (if present, otherwise ignore)
    @Test
    public void testEasterEgg4() {
        game.makeGuess("lion");
    }

    // Check for Easter Egg output (if present, otherwise ignore)
    @Test
    public void testEasterEgg5() {
        game.makeGuess("li");
        game.makeGuess("on");
    }

    // Check for Easter Egg output (if present, otherwise ignore)
    @Test
    public void testEasterEgg6() {
        game.makeGuess("lioness");
    }







}