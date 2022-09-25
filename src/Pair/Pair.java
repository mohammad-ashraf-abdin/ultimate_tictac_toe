package Pair;

import Board.*;

public class Pair<T, T1> {
    public Integer num;
    public Board S;
    public UltimateBoard B;
    public int x,y;

    public Pair(Integer f, Board s) {
        this.num = f;
        S = s;
    }

    public Pair(Integer f, UltimateBoard b) {
        this.num = f;
        B = b;
    }

    public Pair(Integer x, Integer y) {
        this.x= x;
        this.y =y;
    }
}
