package com.wct.TankWar03;

/**
 * @author WenCT
 */
public class Hero extends Tank {
    private int life;//血量大小
    public Hero(int x, int y, int direct, int life) {
        super(x, y, direct);
        this.life = life;
        this.blood.setBlood(life);
    }

    //射击

}
