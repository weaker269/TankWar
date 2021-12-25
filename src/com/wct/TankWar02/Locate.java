package com.wct.TankWar02;

import java.util.Objects;

/**
 * @author WenCT
 */
class Locate {
    int x;
    int y;

    public Locate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Locate locate = (Locate) o;
        return x == locate.x && y == locate.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
