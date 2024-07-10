import java.util.Scanner;
import java.nio.charset.StandardCharsets;

public class Main {
    static Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8.name());

    public static void main(String[] args) {
        boolean keepPlaying = true;

        System.out.println("Welcome to the Hangman Game!");

        while (keepPlaying) {
            Game game = new Game.Builder().setName("Player").build();
            playGame(game);

            System.out.println("Do you want to play another round? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("yes")) {
                keepPlaying = false;
            }
        }

        System.out.println("Thank you for playing! Goodbye!");
    }

    private static void playGame(Game game) {
        System.out.println("Starting a new game...");
        System.out.println("Current word: " + game.getAnswer());

        while (game.getGameStatus() == 0) {
            System.out.println("Make a guess: ");
            String guess = scanner.nextLine();
            double result = game.makeGuess(guess);
            System.out.println("Result: " + result);
            System.out.println("Current points: " + game.getPoints());
            System.out.println("Correct letters count: " + game.countCorrectLetters());
        }

        if (game.getGameStatus() == 1) {
            System.out.println("Congratulations! You've won! The word was: " + game.getAnswer());
        } else {
            System.out.println("Game over! You've lost! The word was: " + game.getAnswer());
        }
    }
}
