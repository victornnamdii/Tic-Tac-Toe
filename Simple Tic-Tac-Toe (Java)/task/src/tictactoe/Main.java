package tictactoe;

import java.util.Scanner;

public class Main {
    public static char winner = 'N';

    public static char[][] gameArray = {
            {'_', '_', '_'},
            {'_', '_', '_'},
            {'_', '_', '_'},
    };

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)){
            printGridAndCheckWinner();

            int index = 0;
            while (winner == 'N' && index < 9) {
                String coordinates = scanner.nextLine();
                char Player = index % 2 == 0 ? 'X' : 'O';

                try {
                    fillArray(coordinates, Player);
                } catch (NumberFormatException e) {
                    System.out.println("You should enter numbers!");
                    continue;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                printGridAndCheckWinner();
                index++;
            }

            if (winner != 'N') {
                System.out.println(winner + " wins");
            } else {
                System.out.println("Draw");
            }
        }

    }

    public static void printGridAndCheckWinner() {
        System.out.printf("""
                        ---------
                        | %c %c %c |
                        | %c %c %c |
                        | %c %c %c |
                        ---------
                        """,
                gameArray[0][0],
                gameArray[0][1],
                gameArray[0][2],
                gameArray[1][0],
                gameArray[1][1],
                gameArray[1][2],
                gameArray[2][0],
                gameArray[2][1],
                gameArray[2][2]
        );

        checkRows();
        checkColumns();
        checkDiagonals();
    }

    public static void checkRows() {
        if (
                gameArray[0][0] == gameArray[0][1] &&
                        gameArray[0][1] == gameArray[0][2] &&
                        gameArray[0][0] != '_'
        ) {
            winner = gameArray[0][0];
        }

        if (
                gameArray[1][0] == gameArray[1][1] &&
                        gameArray[1][1] == gameArray[1][2] &&
                        gameArray[1][0] != '_'
        ) {
            winner = gameArray[1][0];
        }

        if (
                gameArray[2][0] == gameArray[2][1] &&
                        gameArray[2][1] == gameArray[2][2] &&
                        gameArray[2][0] != '_'
        ) {
            winner = gameArray[2][0];
        }
    }

    public static void checkColumns() {
        if (
                gameArray[0][0] == gameArray[1][0] &&
                        gameArray[1][0] == gameArray[2][0] &&
                        gameArray[0][0] != '_'
        ) {
            winner = gameArray[0][0];
        }

        if (
                gameArray[0][1] == gameArray[1][1] &&
                        gameArray[1][1] == gameArray[2][1] &&
                        gameArray[0][1] != '_'
        ) {
            winner = gameArray[0][1];
        }

        if (
                gameArray[0][2] == gameArray[1][2] &&
                        gameArray[1][2] == gameArray[2][2] &&
                        gameArray[0][2] != '_'
        ) {
            winner = gameArray[0][2];
        }
    }

    public static void checkDiagonals() {
        if (
                gameArray[0][0] == gameArray[1][1] &&
                        gameArray[1][1] == gameArray[2][2] &&
                        gameArray[0][0] != '_'
        ) {
            winner = gameArray[0][0];
        }

        if (
                gameArray[0][2] == gameArray[1][1] &&
                        gameArray[1][1] == gameArray[2][0] &&
                        gameArray[0][2] != '_'
        ) {
            winner = gameArray[0][2];
        }
    }

    public static void fillArray(String coordinates, char Player) {
        String[] coordinatesArray = coordinates.split(" ");
        int row = Integer.parseInt(coordinatesArray[0]);
        int column = Integer.parseInt(coordinatesArray[1]);

        char value = gameArray[row - 1][column - 1];
        if (value == 'X' || value == 'O') {
            throw new IllegalArgumentException(
                    "This cell is occupied! Choose another one!"
            );
        }

        gameArray[row - 1][column - 1] = Player;
    }
}
