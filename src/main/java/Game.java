import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class for handling some game logic for hangman game.
 * Every game starts with a score of 10 and the points are reduced based on the description of "makeGuess".
 * Points holds the current score for one game. Game is lost when the user made 10 guesses and did not guess the word.
 *
 */
public class Game {

    /** Holds the points for the game. */
    public int points;

//    /** Holds the round of the game. */
//    int round;

    /** Holds the player name for the game. */
    String name;

    /** Holds the answer for the current game. */
    String answer;

//    /** The path to the file holding the leaderboard. */
//    private String leaderboard = "leaderboard.txt";

    /** The status of the game. {0 - In progress, 1 - Game won, 2 - game over} */
    protected int gameStatus = 0;

    // all things that were already guessed, needs to be cleared for each game
    ArrayList<String> guesses = new ArrayList<>();

    // all answers from makeGuess that were already returned
    ArrayList<Double> answers = new ArrayList<>();

    /**
     * Gets the name for the game.
     * @return String The name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the answer for the game.
     * @return String The Answer.
     */
    public String getAnswer() {
        return this.answer.toLowerCase();
    }

    /**
     * Gets the current status of the game.
     * @return int The current game status.
     */
    public int getGameStatus() {
        return this.gameStatus;
    }

    /**
     * Sets the score for the game.
     * @param points The points to set.
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Gets the score for the game.
     * @return int The current points.
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Counts the number of letters that have been guessed correctly during the game.
     * The number of correct letter guesses will be returned in result.
     * @return int The number of correct letters.
     */
    public int countCorrectLetters() {
        int result = 0;
        if (!guesses.isEmpty()) {
            for (int i = 0; i < this.answer.length(); i++) {
                String current = String.valueOf(this.answer.charAt(i));
                if (guesses.contains(current)) {
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * Counts how often a letter occurs.
     * @param letter The letter to count.
     * @return int The count of the letter.
     */
    public int countLetters(char letter) {
        int count = 0;
        int i = 0;
        while (this.getAnswer().indexOf(letter, i) >= 0) {
            i = this.getAnswer().indexOf(letter, i) + 1;
            count++;
        }
        return count;
    }

    /**
     * Constructs a new game with a random word.
     * @param name The name of the player.
     */
    public Game(String name) {
        this.name = name;
        setRandomWord();
        setPoints(5);
    }

    /**
     * Constructs a new game with a given word and given name.
     * @param fixedWord The fixed word for the game.
     * @param name The name of the player.
     */
    public Game(String fixedWord, String name) {
        this.name = name;
        this.answer = fixedWord;
        setPoints(10);
    }

    /**
     * Constructs a new game with no arguments, empty name and answer.
     */
    public Game() {
        this.name = "";
        this.answer = "";
        setPoints(10);
    }

    /**
     * Sets the name and answers of an already existing game and clears the guesses.
     * @param answer The answer to set.
     * @param name The name of the player.
     */
    public void initGame(String answer, String name) {
        this.name = name;
        this.answer = answer;
        this.gameStatus = 0;
        this.guesses.clear();
        this.answers.clear();
        setPoints(10);
    }

    /**
     * Checks if the guess made is correct, should ignore upper/lower case. Should give points based on made guess.
     * Method returns a double, number of the double has different meanings:
     * 0 Correct guess
     * 1.x Letter is in the word, x represents the number of times the letter is in the word
     * 2.0 Guess has correct length
     * 2.1 Guess is too long (only if it was a word)
     * 2.2 Guess is too short (only if it was a word)
     * 3.0 Guess is partially included in the word (only if it was a word), given instead of 2.2 if it is a partial word
     * 4.0 This guess was already used
     * 4.1 Guess includes symbols, numbers (not just letters or one letter)
     * 5. After 10 guesses the game ends and is set to game over
     * 5.1 If the player keeps guessing even though the status is not InProgress
     *
     * @param guess The guess made by the player.
     * @return double The result of the guess.
     */
    public double makeGuess(String guess) {
        if (gameStatus != 0) {
            return 5.1;
        }

        guess = guess.toLowerCase();

        if (guesses.size() >= 10) {
            gameStatus = 2;  // Set status to game over
            return 5.0;
        }

        if (guesses.contains(guess)) {
            points -= 2;
            guesses.add(guess);
            answers.add(4.0);
            return 4.0;
        }

        if (!guess.matches("[a-z]+")) {
            points -= 3;
            guesses.add(guess);
            answers.add(4.1);
            return 4.1;
        }

        guesses.add(guess);

        if (guess.length() == 1) {
            int occurrences = countLetters(guess.charAt(0));
            if (occurrences > 0) {
                points += occurrences;
                answers.add(1.0 + occurrences * 0.1);
                return 1.0 + occurrences * 0.1;
            } else {
                points -= 1;
                answers.add(1.0);
                return 1.0;
            }
        }

        if (guess.length() != answer.length()) {
            points -= Math.abs(guess.length() - answer.length());
            answers.add(guess.length() > answer.length() ? 2.1 : 2.2);
            return guess.length() > answer.length() ? 2.1 : 2.2;
        }

        if (guess.equals(answer)) {
            points += answer.length();
            gameStatus = 1;
            answers.add(0.0);
            return 0.0;
        }

        points -= 1;
        if (answer.contains(guess)) {
            points += 2;
            answers.add(3.0);
            return 3.0;
        }

        points -= 1;
        answers.add(2.0);
        return 2.0;
    }

    /**
     * Pulls out a random animal and sets it as answer.
     */
    public void setRandomWord() {
        String[] animals = {"dog", "horse", "pony", "cat", "lion", "bear", "lioncub"};

        int randomNum = (int) (Math.floor(Math.random() * (100 - 2 + 1) + 2) % animals.length);
        this.answer = animals[randomNum];
    }
}
