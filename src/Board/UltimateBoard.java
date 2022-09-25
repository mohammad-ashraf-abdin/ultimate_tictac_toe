package Board;

import java.util.LinkedList;
import java.util.List;
public class UltimateBoard {
    int last_move_r = -1;
    int last_move_c = -1;
    public int UltimateWidth;
    public Board[][] UltimateBoard;

    public UltimateBoard(int ultimateWidth) {
        this.UltimateWidth = ultimateWidth;
        UltimateBoard = new Board[ultimateWidth][ultimateWidth];
        for (int i = 0; i < ultimateWidth; i++) {
            for (int j = 0; j < ultimateWidth; j++) {
                UltimateBoard[i][j] = new Board(ultimateWidth, true);
            }
        }
    }

    public UltimateBoard(UltimateBoard ultimateBoard) {
        this.UltimateWidth = ultimateBoard.UltimateWidth;
        this.last_move_c = ultimateBoard.last_move_c;
        this.last_move_r = ultimateBoard.last_move_r;
        UltimateBoard = new Board[UltimateWidth][UltimateWidth];
        for (int i = 0; i < UltimateWidth; i++) {
            for (int j = 0; j < UltimateWidth; j++) {
                UltimateBoard[i][j] = new Board(ultimateBoard.UltimateBoard[i][j]);
            }
        }

    }

    public void play(int x, int y, int cell, char c) {
        int x2 = -1, y2 = -1;
        x2 = (cell-1) / (this.UltimateWidth);
        if (x2 ==0)
            y2 = (cell-1)%this.UltimateWidth;
        else
            y2 = (cell-1)%(x2*this.UltimateWidth) ;
        if (this.UltimateBoard[x2][y2].Availble) {
            if (this.UltimateBoard[x2][y2].SBoard[x][y] == ' ') {
                this.UltimateBoard[x2][y2].SBoard[x][y] = c;
                this.UltimateBoard[x2][y2].last_move_r = x;
                this.UltimateBoard[x2][y2].last_move_c = y;
                this.last_move_r = x2;
                this.last_move_c = x2;
                if (this.UltimateBoard[x2][y2].isWin('O')) {
                    this.UltimateBoard[x2][y2].Availble = false;
                    this.UltimateBoard[x2][y2].MainOwner = 'O';
                } else if (this.UltimateBoard[x2][y2].isWin('X')) {
                    this.UltimateBoard[x2][y2].Availble = false;
                    this.UltimateBoard[x2][y2].MainOwner = 'X';
                }
            }
        }
        if (this.UltimateBoard[x][y].MainOwner == ' ')
            for (int i = 0; i < UltimateWidth; i++)
                for (int j = 0; j < UltimateWidth; j++) {

//              System.out.println(i+" "+j+" "+x+" "+y+" ");
                    if (i == x && y == j)
                        this.UltimateBoard[i][j].Availble = true;
                    else
                        this.UltimateBoard[i][j].Availble = false;
                }
        else {
            for (int i = 0; i < UltimateWidth; i++)
                for (int j = 0; j < UltimateWidth; j++) {
                    if (this.UltimateBoard[i][j].MainOwner == ' ')
                        this.UltimateBoard[i][j].Availble = true;
                    else
                        this.UltimateBoard[i][j].Availble = false;
                }
        }


    }


    public List<UltimateBoard> allNextMoves() {
        List<UltimateBoard> nextBoards = new LinkedList<>();
        for (int i = 0; i < UltimateWidth; i++) {
            for (int j = 0; j < UltimateWidth; j++) {
                if (this.UltimateBoard[i][j].Availble) {
                    for (int k = 0; k < UltimateWidth; k++)
                        for (int l = 0; l < UltimateWidth; l++) {
                            UltimateBoard nextBoard = new UltimateBoard(this);
                            nextBoard.play(k, l, ((UltimateWidth * i + j) + 1), 'X');
                            if (nextBoard.UltimateBoard[i][j].last_move_c != -1)
                                nextBoards.add(nextBoard);
//                        }

                        }
                }
            }
        }
        return nextBoards;
    }
    private boolean allMain_X(List<Board> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).MainOwner != 'X') {
                return false;
            }

        }
        return true;
    }

    private boolean allMain_O(List<Board> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).MainOwner != 'O') {
                return false;
            }
        }
        return true;
    }

    public boolean isWinner(char player) {

        int row = this.last_move_r;
        int col = this.last_move_c;
        //check row
        List<Board> collected_row = new LinkedList<>();
        for (int c = 0; c < UltimateWidth; c++) {
            collected_row.add(UltimateBoard[row][c]);
        }
        if (allMain_X(collected_row) && player == 'X') {
            return true;
        } else if (allMain_O((collected_row)) && player == 'O') {
            return true;
        }
        //check row
        List<Board> collected_col = new LinkedList<>();
        for (int r = 0; r < UltimateWidth; r++) {
            collected_col.add(UltimateBoard[r][col]);
        }
        if (allMain_X(collected_col) && player == 'X') {
            return true;
        } else if (allMain_O((collected_col)) && player == 'O') {
            return true;
        }
        // check diag 1
        if (row == col) {
            List<Board> collected_d1 = new LinkedList<>();
            for (int i = 0; i < UltimateWidth; i++) {
                collected_d1.add(UltimateBoard[i][i]);
            }
            if (allMain_X(collected_d1) && player == 'X') {
                return true;
            } else if (allMain_O((collected_d1)) && player == 'O') {
                return true;
            }
        }

        // check diag 2
        if (row == (UltimateWidth - (col + 1))) {
            List<Board> collected_d1 = new LinkedList<>();
            for (int i = 0; i < UltimateWidth; i++) {
                collected_d1.add(UltimateBoard[i][UltimateWidth - (i + 1)]);
            }
            if (allMain_X(collected_d1) && player == 'X') {
                return true;
            } else if (allMain_O((collected_d1)) && player == 'O') {
                return true;
            }
        }
        return false;
    }

    public int eval() {

        int Xscore = 0;
        int Yscore = 0;
        int xWin = 0;
        int yWin = 0;
        int win = UltimateWidth * UltimateWidth;
        for (int i = 0; i < UltimateWidth; i++) {
            for (int j = 0; j < UltimateWidth; j++) {
                if (UltimateBoard[i][j].MainOwner == 'X')
                    xWin++;
                if (UltimateBoard[i][j].MainOwner == 'O')
                    xWin++;
            }

            if (xWin == UltimateWidth - 1) {
                Xscore++;
            }
            if (yWin == UltimateWidth - 1) {
                Yscore++;
            }
            xWin = 0;
            yWin = 0;
        }
        for (int i = 0; i < UltimateWidth; i++) {
            for (int j = 0; j < UltimateWidth; j++) {
                if (UltimateBoard[i][j].MainOwner == 'X')
                    xWin++;
                if (UltimateBoard[i][j].MainOwner == 'O')
                    xWin++;
            }

            if (xWin == UltimateWidth - 1) {
                Xscore++;
            }
            if (yWin == UltimateWidth - 1) {
                Yscore++;
            }
            xWin = 0;
            yWin = 0;
        }
        for (int i = 0; i < UltimateWidth; i++) {
            for (int j = 0; j < UltimateWidth; j++) {
                if (i==j)
                    if (UltimateBoard[i][j].MainOwner == 'X')
                        xWin++;
                if (UltimateBoard[i][j].MainOwner == 'O')
                    xWin++;
            }

            if (xWin == UltimateWidth - 1) {
                Xscore++;
            }
            if (yWin == UltimateWidth - 1) {
                Yscore++;
            }
            xWin = 0;
            yWin = 0;
        }
        for (int i = 0; i < UltimateWidth; i++) {
            for (int j = 0; j < UltimateWidth; j++) {
                if(i == (j - (i + 1)))
                    if (UltimateBoard[i][j].MainOwner == 'X')
                        xWin++;
                if (UltimateBoard[i][j].MainOwner == 'O')
                    xWin++;
            }

            if (xWin == UltimateWidth - 1) {
                Xscore++;
            }
            if (yWin == UltimateWidth - 1) {
                Yscore++;
            }
            xWin = 0;
            yWin = 0;
        }
        return Xscore-Yscore;
    }
}
