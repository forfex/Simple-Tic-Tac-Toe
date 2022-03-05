package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        game();
    }

    public static void game() {
        Scanner scanner = new Scanner(System.in);
        String test = "_________";
        String[] list = test.split("");

        render(list);
        inputCoordinates(list, scanner, "X");
    }

    public static void render(String[] game) {
        for (int i = 0; i < game.length; i++) {
            if (game[i].equals("_")) game[i] = " ";
        }
        System.out.println("---------");
        System.out.printf("| %s %s %s |\n", game[0], game[1], game[2]);
        System.out.printf("| %s %s %s |\n", game[3], game[4], game[5]);
        System.out.printf("| %s %s %s |\n", game[6], game[7], game[8]);
        System.out.println("---------");
    }

    public static void inputCoordinates(String[] game, Scanner scanner, String player) {
        int x, y;
        System.out.print("Enter the coordinates: ");
        y = scanner.nextInt();
        x = scanner.nextInt();
        if (x > 3 || x < 1 || y > 3 || y < 1) {
            System.out.println("Coordinates should be from 1 to 3!");
            inputCoordinates(game ,scanner, player);
            return;
        }
        placeAtCoords(game, scanner, x - 1, y - 1, player);
    }

    public static void placeAtCoords(String[] game, Scanner scanner, int x, int y, String player) {
        int n = y * 3 + x;
        if (!game[n].equals(" ")) {
            System.out.println("This cell is occupied! Choose another one!");
            inputCoordinates(game ,scanner, player);
            return;
        }
        game[n] = player;
        render(game);
        if (isWinner(player, game)) {
            System.out.printf("%s wins", player);
            return;
        }
        if (isDraw(game)) return;

        switch (player) {
            case "X":
                player = "O";
                break;
            case "O":
                player = "X";
                break;
        }
        inputCoordinates(game ,scanner, player);
    }

    public static Boolean isDraw(String[] game) {
        int xo = 0, xs = 0;
        for (String s : game) {
            if (s.equals("X")) xs++;
            if (s.equals("O")) xo++;
        }
        if (xs + xo == 9) {
            System.out.println("Draw");
            return true;
        } else {
            return false;
        }
    }

    public static Boolean isWinner(String player, String[] game) {
        for (int i = 0; i < 3; i++) {
            if (game[i].equals(player) && game[i + 3].equals(player) && game[i + 6].equals(player)) return true;
        }
        for (int i = 0; i < 3; i++) {
            int x = i * 3;
            if (game[x].equals(player) && game[x + 1].equals(player) && game[x + 2].equals(player)) return true;
        }
        if (game[4].equals(player)) {
            if (game[0].equals(player) && game[8].equals(player)) return true;
            return game[2].equals(player) && game[6].equals(player);
        }
        return false;
    }
}