import java.util.Random;
import java.util.Scanner;

public class Connect4 {
    public static final int ROWS = 6;
    public static final int COLUMNS = 7;
    public static final char EMPTY_SLOT = '-';
    public static final char PLAYER_TOKEN = 'X';
    public static final char COMPUTER_TOKEN = 'O';

    public static void main(String[] args) {
        char[][] board = new char[ROWS][COLUMNS];
        initializeBoard(board);
        printBoard(board);

        char currentPlayer = PLAYER_TOKEN;
        boolean gameWon = false;
        Scanner scanner = new Scanner(System.in);

        while (!gameWon && !isBoardFull(board)) {
            if (currentPlayer == PLAYER_TOKEN) {
                System.out.println("Player, enter a column (0-6): ");
                int column = scanner.nextInt();

                while (column < 0 || column >= COLUMNS || !isColumnValid(board, column)) {
                    System.out.println("Invalid column. Try again:");
                    column = scanner.nextInt();
                }

                int row = dropToken(board, column, currentPlayer);
                printBoard(board);

                if (checkForWin(board, row, column, currentPlayer)) {
                    System.out.println("Player wins!");
                    gameWon = true;
                }
                currentPlayer = COMPUTER_TOKEN;
            } else {
                int column = getComputerMove(board);
                System.out.println("Computer chooses column: " + column);
                int row = dropToken(board, column, currentPlayer);
                printBoard(board);

                if (checkForWin(board, row, column, currentPlayer)) {
                    System.out.println("Computer wins!");
                    gameWon = true;
                }
                currentPlayer = PLAYER_TOKEN;
            }
        }

        if (!gameWon) {
            System.out.println("The game is a draw!");
        }

        scanner.close();
    }

    public static void initializeBoard(char[][] board) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = EMPTY_SLOT;
            }
        }
    }

    public static void printBoard(char[][] board) {
        System.out.println();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("0 1 2 3 4 5 6");
    }

    public static boolean isBoardFull(char[][] board) {
        for (int j = 0; j < COLUMNS; j++) {
            if (board[0][j] == EMPTY_SLOT) {
                return false;
            }
        }
        return true;
    }

    public static boolean isColumnValid(char[][] board, int column) {
        return board[0][column] == EMPTY_SLOT;
    }

    public static int dropToken(char[][] board, int column, char token) {
        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][column] == EMPTY_SLOT) {
                board[i][column] = token;
                return i;
            }
        }
        return -1;
    }

    public static int getComputerMove(char[][] board) {
        Random random = new Random();
        int column = random.nextInt(COLUMNS);
        while (!isColumnValid(board, column)) {
            column = random.nextInt(COLUMNS);
        }
        return column;
    }

    public static boolean checkForWin(char[][] board, int row, int col, char token) {
        return checkDirection(board, row, col, token, 1, 0) 
            || checkDirection(board, row, col, token, 0, 1)  
            || checkDirection(board, row, col, token, 1, 1)  
            || checkDirection(board, row, col, token, 1, -1); 
    }


    public static boolean checkDirection(char[][] board, int row, int col, char token, int rowDelta, int colDelta) {
        int count = 1;

        for (int i = 1; i < 4; i++) {
            int newRow = row + i * rowDelta;
            int newCol = col + i * colDelta;
            if (newRow >= 0 && newRow < ROWS && newCol >= 0 && newCol < COLUMNS && board[newRow][newCol] == token) {
                count++;
            } else {
                break;
            }
        }

        for (int i = 1; i < 4; i++) {
            int newRow = row - i * rowDelta;
            int newCol = col - i * colDelta;
            if (newRow >= 0 && newRow < ROWS && newCol >= 0 && newCol < COLUMNS && board[newRow][newCol] == token) {
                count++;
            } else {
                break;
            }
        }

        return count >= 4;
    }
}