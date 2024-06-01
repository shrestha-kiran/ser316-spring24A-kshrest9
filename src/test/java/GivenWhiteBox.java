import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;


public class GivenWhiteBox {
    Game game;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void startGame() {
        Game game = new Game("lion", "Dr. M");
        assertEquals(10, game.getPoints());
    }

}
