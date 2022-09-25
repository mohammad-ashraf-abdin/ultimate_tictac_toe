package Board;

import java.util.LinkedList;
import java.util.List;

public class Board {
    private int width = 3;
    public char[][] SBoard;
    char MainOwner = ' ';
    public boolean Availble = true;
    public int last_move_r = -1;
    public int last_move_c = -1;

    public Board(int width, boolean availble) {
        this.width = width;
        this.Availble = availble;
        this.MainOwner = ' ';
        this.last_move_r = -1;
        this.last_move_c = -1;
        SBoard = new char[width][width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                SBoard[i][j] = ' ';
            }
        }
    }

    public Board(Board state) {
        this.width = state.width;
        this.Availble = state.Availble;
        this.MainOwner = state.MainOwner;
        this.last_move_c = state.last_move_c;
        this.last_move_r = state.last_move_r;
        SBoard = new char[width][width];
        for (int i = 0; i < width; i++) {
            System.arraycopy(state.SBoard[i], 0, SBoard[i], 0, width);
        }

    }

    public char getMainOwner() {
        return MainOwner;
    }

    public int getWidth() {
        return this.width;
    }

    public void play(int x, int y, char c) {
        if (SBoard[x][y] == ' ') {
            SBoard[x][y] = c;
        }
        this.last_move_r = x;
        this.last_move_c = y;

    }

    public List<Board> allNextMoves() {
        List<Board> nextBoards = new LinkedList<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (SBoard[i][j] == ' ') {
                    Board nextBoard = new Board(this);
                    nextBoard.play(i, j, 'X');
                    if (nextBoard.last_move_c != -1)
                        nextBoards.add(nextBoard);

                }
            }
        }
        return nextBoards;
    }

    public boolean all_X(List<Character> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != 'X') {
                return false;
            }
        }
        return true;
    }

    public boolean all_O(List<Character> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != 'O') {
                return false;
            }
        }
        return true;
    }

    public boolean isWin(char player) {

        int row = this.last_move_r;
        int col = this.last_move_c;
        if (row==-1)
            return false;
        List<Character> collected_row = new LinkedList<>();
        for (int c = 0; c < width; c++) {
            collected_row.add(SBoard[row][c]);
        }
        if (all_X(collected_row) && player == 'X') {
            return true;
        } else if (all_O((collected_row)) && player == 'O') {
            return true;
        }
        List<Character> collected_col = new LinkedList<>();
        for (int r = 0; r < width; r++) {
            collected_col.add(SBoard[r][col]);
        }
        if (all_X(collected_col) && player == 'X') {
            return true;
        } else if (all_O((collected_col)) && player == 'O') {
            return true;
        }
        if (row == col) {
            List<Character> collected_d1 = new LinkedList<>();
            for (int i = 0; i < width; i++) {
                collected_d1.add(SBoard[i][i]);
            }
            if (all_X(collected_d1) && player == 'X') {
                return true;
            } else if (all_O((collected_d1)) && player == 'O') {
                return true;
            }
        }
        if (row == (width - (col + 1))) {
            List<Character> collected_d1 = new LinkedList<>();
            for (int i = 0; i < width; i++) {
                collected_d1.add(SBoard[i][width - (i + 1)]);
            }
            if (all_X(collected_d1) && player == 'X') {
                return true;
            } else if (all_O((collected_d1)) && player == 'O') {
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
        int win = width * width;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (SBoard[i][j]== 'X')
                    xWin++;
                if (SBoard[i][j]== 'O')
                    xWin++;
            }

            if (xWin == width - 1) {
                Xscore++;
            }
            if (yWin == width - 1) {
                Yscore++;
            }
            xWin = 0;
            yWin = 0;
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (SBoard[i][j]== 'X')
                    xWin++;
                if (SBoard[i][j]== 'O')
                    xWin++;
            }

            if (xWin == width - 1) {
                Xscore++;
            }
            if (yWin == width - 1) {
                Yscore++;
            }
            xWin = 0;
            yWin = 0;
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (i==j)
                    if (SBoard[i][j]== 'X')
                        xWin++;
                if (SBoard[i][j]== 'O')
                    xWin++;
            }

            if (xWin == width - 1) {
                Xscore++;
            }
            if (yWin == width - 1) {
                Yscore++;
            }
            xWin = 0;
            yWin = 0;
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if(i == (j - (i + 1)))
                    if (SBoard[i][j]== 'X')
                        xWin++;
                if (SBoard[i][j]== 'O')
                    xWin++;
            }

            if (xWin == width - 1) {
                Xscore++;
            }
            if (yWin == width - 1) {
                Yscore++;
            }
            xWin = 0;
            yWin = 0;
        }
        return Xscore-Yscore;
    }

}
