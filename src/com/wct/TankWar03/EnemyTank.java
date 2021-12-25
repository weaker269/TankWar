package com.wct.TankWar03;

/**
 * @author WenCT
 */
public class EnemyTank extends Tank implements Runnable{
    private int life;
    public EnemyTank(int x, int y, int direct, int life) {
        super(x, y, direct);
        this.life = life;
        this.blood.setBlood(life);
    }
    @Override
    public void run() {
        while(true){
            switch (getDirect()){
                case 0:
                    for(int i = 0 ; i < 50 ; i++){
                        if(getY()>0) moveUp();
                        else break;
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if((int)(Math.random()*30) == 0 ) target();
                        this.area.getMap().clear();
                        this.area.fillMap(getX(),getY(),getDirect());
                    }
                    break;
                case 1:
                    for(int i = 0 ; i < 50 ; i++){
                        if(getX() + 75 < 1000) moveRight();
                        else break;
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if((int)(Math.random()*30) == 0 ) target();
                        this.area.getMap().clear();
                        this.area.fillMap(getX(),getY(),getDirect());
                    }
                    break;
                case 2:
                    for(int i = 0 ; i < 50 ; i++){
                        if(getY()+100 < 750)moveDown();
                        else break;
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if((int)(Math.random()*30) == 0 ) target();
                        this.area.getMap().clear();
                        this.area.fillMap(getX(),getY(),getDirect());
                    }
                    break;
                case 3:
                    for(int i = 0 ; i < 50 ; i++){
                        if(getX() > 0)moveLeft();
                        else break;
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if((int)(Math.random()*30) == 0 ) target();
                        this.area.getMap().clear();
                        this.area.fillMap(getX(),getY(),getDirect());
                    }
                    break;
            }
            this.setDirect((int)(Math.random()*4));
            if(!this.isLive()) break;
            removeBullet();
        }
    }
}
