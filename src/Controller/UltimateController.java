package Controller;

import Board.*;
import Pair.Pair;

import java.util.Scanner;

public class UltimateController {


    UltimateBoard Uboard = new UltimateBoard(3);

    void printGrid(UltimateBoard temp) {
        for (int i = 0; i < temp.UltimateWidth; i++) {

            for (int j = 0; j < temp.UltimateWidth; j++) {
                for (int i2 = 0; i2 < temp.UltimateWidth; i2++) {
                    for (int j2 = 0; j2 < temp.UltimateWidth; j2++) {
                        System.out.print(temp.UltimateBoard[i][i2].SBoard[j][j2] + " ");

                    }

                    System.out.print("| ");
                }

                System.out.println("");

            }
            System.out.println("------------------------");
        }
    }

    public void gameOn() {
        printGrid(Uboard);
        while (true) {
            System.out.println("************USER************");
            getUserMove();
            printGrid(Uboard);
            if (Uboard.isWinner('O')) {
                System.out.println("User Wins");
                break;
            }
            System.out.println("************Computer************");
            getComputerMove();
            printGrid(Uboard);
            if (Uboard.isWinner('X')) {
                System.out.println("Computer Wins");
                break;
            }
        }

    }


    private void getUserMove() {
        Scanner s = new Scanner(System.in);
        int cellCounter = 0, cellx = -1, celly = -1;
        for (int i = 0; i < Uboard.UltimateWidth; i++)
            for (int j = 0; j < Uboard.UltimateWidth; j++) {
                if (Uboard.UltimateBoard[i][j].Availble) {
                    cellCounter++;
                    cellx = i;
                    celly = j;
                }
            }
        int cell, row, col;
        if (cellCounter != 1) {
            while (true) {
                System.out.print("Enter cell: ");
                cell = s.nextInt();
                System.out.println();
                if ((cell > 0) && (cell < (Uboard.UltimateWidth + 1) * (Uboard.UltimateWidth + 1))) {
                    break;
                }
            }
        } else {
            System.out.println("play on cell : " + ((cellx * 3 + celly) + 1));
            cell = (cellx * 3 + celly) + 1;
        }

        while (true) {
            System.out.print("Enter row: ");
            row = s.nextInt();
            System.out.println();
            if ((row > 0) && (row < Uboard.UltimateWidth + 1)) {
                break;
            }
        }
        while (true) {
            System.out.print("Enter column: ");
            col = s.nextInt();
            System.out.println();
            if ((col > 0) && (col < Uboard.UltimateWidth + 1)) {
                break;
            }
        }
        Uboard.play(row - 1, col - 1, cell, 'O');
    }

    private void getComputerMove() {
//        board = board.allNextMoves().get(0);


        Uboard = maxalphaMove(Uboard, -9999, 9999, 3).B;
    }


    private Pair maxalphaMove(UltimateBoard b, int alpha, int beta, int depth) {

        Pair board = null;
        int newnum = 0;

        if (b != null && (b.isWinner('X') || depth == 0)) {
            board = new Pair(b.eval(), b);


            return board;
        }
        int maxValue = -999999;


        for (UltimateBoard possiblemove : b.allNextMoves()) {

            newnum = minalphaMove(possiblemove, alpha, beta, depth - 1).num;

            if (newnum >= maxValue) {

                board = new Pair(newnum, possiblemove);

                maxValue = newnum;
                alpha = Math.max(maxValue, alpha);
                if (alpha >= beta)
                    break;
            }

        }
        return board;
    }

    private Pair minalphaMove(UltimateBoard b, int alpha, int beta, int depth) {
        Pair board = null;
        int newmnum = 0;

        if (b != null && (b.isWinner('X') || depth == 0)) {
            board = new Pair(b.eval(), b);
            return board;
        }
        int mini = 99999;
        for (UltimateBoard nextBoard : b.allNextMoves()) {

            newmnum = maxalphaMove(nextBoard, alpha, beta, depth - 1).num;

            if (newmnum <= mini) {

                board = new Pair(newmnum, nextBoard);

                mini = newmnum;
                beta = Math.min(mini, beta);
                if (alpha >= beta)
                    break;
            }
        }
        return board;
    }

}
