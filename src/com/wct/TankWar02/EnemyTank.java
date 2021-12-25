package com.wct.TankWar02;

/**
 * @author WenCT
 */
public class EnemyTank extends Tank implements Runnable{
    public EnemyTank(int x, int y, int direct) {
        super(x, y, direct);
    }
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!this.isLive()) break;
            removeBullet();
            target();
        }
    }
}
