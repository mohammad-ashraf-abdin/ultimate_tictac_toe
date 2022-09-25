package Controller;

import Board.Board;

import Pair.Pair;

public class Controller {

    Board board = new Board(3,true);

    public Controller(Board board2) {
        this.board = new Board(board2) ;
        this.board.last_move_r = board2.last_move_r;
        this.board.last_move_c = board2.last_move_c;
    }

    public Pair getComputerMove(Board temp) {
        Pair pair = comp(temp, maxalphaMove(temp,-9999,9999,5).S);
        return pair;
    }
    private Pair comp(Board b1, Board b2) {
        Pair pair = new Pair<Integer, Integer>(0,0); ;

        for (int i = 0; i < b1.getWidth(); i++) {
            for (int j = 0; j < b1.getWidth(); j++) {
                if (b1.SBoard[i][j] != b2.SBoard[i][j])
                     pair = new Pair<Integer, Integer>(i,j);

            }
    }
        return pair;

    }


    private Pair maxalphaMove(Board b, int alpha , int beta, int depth) {

        Pair board = null;
        int newnum = 0;

        if (b.isWin('X') ||depth == 0)
        {
            board = new Pair(b.eval(), b);


            return board;
        }
        int maxValue = -999999;


        for (Board possiblemove : b.allNextMoves())
        {

            newnum = minalphaMove(possiblemove,alpha,beta,depth-1).num;

            if (newnum >= maxValue)
            {

                board = new Pair(newnum, possiblemove);

                maxValue = newnum;
                alpha = Math.max(maxValue,alpha);
                if(alpha>=beta)
                    break;
            }

        }
        return board;
    }

    private Pair minalphaMove(Board b, int alpha , int beta, int depth) {
        Pair board = null;
        int newmnum=0;

        if (b.isWin('X')||depth==0)
        {
            board = new Pair(b.eval(), b);
            return board;
        }
        int mini = 99999;
        for (Board nextBoard : b.allNextMoves())
        {

            newmnum = maxalphaMove(nextBoard,alpha,beta,depth-1).num;

            if (newmnum <= mini)
            {

                board = new Pair(newmnum, nextBoard);

                mini = newmnum;
                beta = Math.min(mini,beta);
                if(alpha>=beta)
                    break;
            }
        }
        return board;
    }

}
