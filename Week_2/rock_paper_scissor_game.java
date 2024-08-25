import java.util.Random;
import java.util.Scanner;

public class SimpleRockPaperScissors {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String[] choices = {"Rock", "Paper", "Scissors"};

        while (true) {
            System.out.println("Enter Rock, Paper, or Scissors. Type 'exit' to quit:");
            String userChoice = scanner.nextLine().trim();

            if (userChoice.equalsIgnoreCase("exit")) {
                System.out.println("Thanks for playing!");
                break;
            }
            if (!userChoice.equalsIgnoreCase("Rock") &&
                !userChoice.equalsIgnoreCase("Paper") &&
                !userChoice.equalsIgnoreCase("Scissors")) {
                System.out.println("Invalid choice. Try again.");
                continue;
            }
            String computerChoice = choices[random.nextInt(choices.length)];
            System.out.println("Computer chose: " + computerChoice);

            // Determine the winner
            String result = determineWinner(userChoice, computerChoice);
            System.out.println(result);
        }
        scanner.close();
    }

    private static String determineWinner(String userChoice, String computerChoice) {
        if (userChoice.equalsIgnoreCase(computerChoice)) {
            return "It's a tie!";
        } else if (
            (userChoice.equalsIgnoreCase("Rock") && computerChoice.equalsIgnoreCase("Scissors")) ||
            (userChoice.equalsIgnoreCase("Paper") && computerChoice.equalsIgnoreCase("Rock")) ||
            (userChoice.equalsIgnoreCase("Scissors") && computerChoice.equalsIgnoreCase("Paper"))
        ) {
            return "You win!";
        } else {
            return "You lose!";
        }
    }
}
