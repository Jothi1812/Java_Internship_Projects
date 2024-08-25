import java.util.Scanner;

public class TicTacToe {
    static char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char currentPlayer = 'X';
        boolean gameWon = false;

        printBoard();

        for (int i = 0; i < 9 && !gameWon; i++) {
            int row, col;
            do {
                System.out.println("Player " + currentPlayer + ", enter your move (row and column): ");
                row = scanner.nextInt() - 1;
                col = scanner.nextInt() - 1;
            } while (!isValidMove(row, col));

            board[row][col] = currentPlayer;
            printBoard();
            gameWon = checkWin();

            if (gameWon) {
                System.out.println("Player " + currentPlayer + " wins!");
            } else if (i == 8) {
                System.out.println("The game is a draw!");
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }

        scanner.close();
    }

    public static void printBoard() {
        System.out.println("--------------");
        for (int i = 0; i < 3; i++) {
            System.out.println("| " + board[i][0] + " | " + board[i][1] + " | " + board[i][2] + " |");
            System.out.println("--------------");
        }
    }

    public static boolean isValidMove(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
            return true;
        } else {
            System.out.println("This move is not valid");
            return false;
        }
    }

    public static boolean checkWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') ||
                (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ')) {
                return true;
            }
        }

        // Check diagonals
        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') ||
            (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ')) {
            return true;
        }

        return false;
    }
}
