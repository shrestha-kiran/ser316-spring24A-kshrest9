import java.util.Scanner;
import java.nio.charset.StandardCharsets;

public class Main {
    static Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8.name());
    public static void main(String[] args) {
        System.out.println("Getting started");
        Game game = new Game("Student");
        System.out.println("Current word: " + game.getAnswer());
        System.out.println(game.makeGuess("a"));
        System.out.println("Automatic guess a");

        // Rough game play
        Game newgame = new Game("Dr. M.");
        System.out.println("Make a guess: ");
        while (newgame.getGameStatus() == 0) {
            String message = scanner.nextLine();
            System.out.println(newgame.makeGuess(message));
            System.out.println("Score: " + newgame.getPoints());
            int correctLetters = newgame.countCorrectLetters();  // SER316 TASK 2 SPOTBUGS FIX
        }

        // Demonstrate game reset
        newgame.resetGame();
        System.out.println("Game reset. New word: " + newgame.getAnswer());
    }
}
