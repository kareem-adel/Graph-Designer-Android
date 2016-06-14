package com.kareem_adel.graphdesigner;

/**
 * Created by Kareem-Adel on 1/14/2016.
 */
public class NodeKey {
    public NodeKey(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int x;
    int y;

    @Override
    public boolean equals(Object o) {
        return (x == ((NodeKey) o).x) && (y == ((NodeKey) o).y);
    }

    @Override
    public int hashCode() {
        return 51 * x + y;
    }
}
