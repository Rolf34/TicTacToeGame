import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner arbuz = new Scanner(System.in);
        boolean isGameOver = false;
        int row = -1, col = -1;
        int gameMode = 0;
        int razmerDoski = 3;

        int rows = razmerDoski * 2 + 1;
        int cols = razmerDoski * 4 + 1;
        char[][] displayBoard = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                displayBoard[i][j] = ' ';
            }
        }
        for (int i = 0; i < razmerDoski; i++) {
            displayBoard[0][2 + i * 4] = (char) ('1' + i);
            displayBoard[2 + i * 2][0] = (char) ('1' + i);
        }
        for (int i = 1; i < rows; i += 2) {
            for (int j = 1; j < cols; j++) {
                displayBoard[i][j] = '-';
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 4; j < cols; j += 5) {
                displayBoard[i][j] = '|';
            }
        }

        System.out.println("Press 1 to play");

        while (gameMode != 1) {
            System.out.println("Cho, gotov(a)?");
            if (arbuz.hasNextInt()) {
                gameMode = arbuz.nextInt();
            } else {
                System.out.println("Budlaska sprobuyte she raz!");
                arbuz.next();
            }
        }

        char igrok = 'X';
        while (!isGameOver) {
            System.out.println("\nZaraz graye: " + igrok);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    System.out.print(displayBoard[i][j]);
                }
                System.out.println();
            }

            while (true) {
                System.out.println("Vvedit ryad:");
                while (!arbuz.hasNextInt()) {
                    System.out.println("Nekorektni koordinaty. Sprobuite she raz");
                    arbuz.next();
                }
                row = arbuz.nextInt();
                System.out.println("Vvedit colonku:");
                while (!arbuz.hasNextInt()) {
                    System.out.println("Nekorektni koordinaty. Sprobuite she raz");
                    arbuz.next();
                }
                col = arbuz.nextInt();

                if (row >= 1 && row <= razmerDoski && col >= 1 && col <= razmerDoski) {
                    int displayRow = (row - 1) * 2 + 2;
                    int displayCol = (col - 1) * 4 + 2;
                    if (displayBoard[displayRow][displayCol] == ' ') {
                        row = displayRow;
                        col = displayCol;
                        break;
                    } else {
                        System.out.println("ZANYATO! Klitynka vze zanyata, sprobuite she raz.");
                    }
                } else {
                    System.out.println("Pomilka! Vvedit chisla vid 1 do " + razmerDoski);
                }
            }

            displayBoard[row][col] = igrok;
            boolean peremogaBude = false;
            for (int i = 2; i < rows; i += 2) {
                int count = 0;
                for (int j = 2; j < cols; j += 4) {
                    if (displayBoard[i][j] == igrok) {
                        count++;
                        if (count == razmerDoski) peremogaBude = true;
                    } else {
                        count = 0;
                    }
                }
            }
            for (int j = 2; j < cols; j += 4) {
                int count = 0;
                for (int i = 2; i < rows; i += 2) {
                    if (displayBoard[i][j] == igrok) {
                        count++;
                        if (count == razmerDoski) peremogaBude = true;
                    } else {
                        count = 0;
                    }
                }
            }
            int countMainDiagonal = 0;
            for (int i = 2, j = 2; i < rows && j < cols; i += 2, j += 4) {
                if (displayBoard[i][j] == igrok) {
                    countMainDiagonal++;
                    if (countMainDiagonal == razmerDoski) peremogaBude = true;
                }
            }
            int countSideDiagonal = 0;
            for (int i = 2, j = cols - 3; i < rows && j >= 2; i += 2, j -= 4) {
                if (displayBoard[i][j] == igrok) {
                    countSideDiagonal++;
                    if (countSideDiagonal == razmerDoski) peremogaBude = true;
                }
            }

            if (peremogaBude) {
                System.out.println("POTUZHNA PEREMOGA " + igrok + "!!!");
                isGameOver = true;
            } else {
                boolean full = true;
                for (int i = 2; i < rows; i += 2) {
                    for (int j = 2; j < cols; j += 4) {
                        if (displayBoard[i][j] == ' ') {
                            full = false;
                        }
                    }
                }
                if (full) {
                    System.out.println("Nichya nachalnika!");
                    isGameOver = true;
                }
            }

            igrok = (igrok == 'X') ? 'O' : 'X';
        }

        System.out.println("finalna doska:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(displayBoard[i][j]);
            }
            System.out.println(); 
        }

        arbuz.close();
    }
}