package com.wct.TankWar03;

/**
 * @author WenCT
 */
public class Blood {
    private int blood;

    public void reduce(int num){
        blood -= num;
    }
    public void addBlood(int num){
        blood += num;
    }
    public Blood() {
    }

    public Blood(int blood) {
        this.blood = blood;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }
}
