package com.wct.TankWar;

import java.util.Vector;

/**
 * @author WenCT
 */
public class Tank implements Runnable{
    private int x; //坦克的横坐标
    private int y; //坦克的纵坐标
    private int direct; //坦克方向 0：上 1：右 2：下 3：左
    private int speed = 1;
    private Vector<Shot> bullet = new Vector<>();
    private int bulletSize;
    public Tank(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            target();
        }
    }

    public Vector<Shot> getBullet() {
        return bullet;
    }

    public void setBullet(Vector<Shot> bullet) {
        this.bullet = bullet;
    }

    public int getBulletSize() {
        return bulletSize;
    }

    public void setBulletSize(int bulletSize) {
        this.bulletSize = bulletSize;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void moveUp(){
        y -= speed;
    }
    public void moveRight(){
        x += speed;
    }
    public void moveDown(){
        y += speed;
    }
    public void moveLeft(){
        x -= speed;
    }
    public void removeBullet(){
        for(int i = 0 ; i < bullet.size();i++){
            if(bullet.get(i).isLive() == false) bullet.remove(i);
        }
    }
    public void target(){
        Shot shot = null;
        switch (getDirect()){
            case 0 : //向上
                shot = new Shot(getX()+20,getY(),0);
                break;
            case 1 : //向右
                shot = new Shot(getX()+60,getY()+20,1);
                break;
            case 2 : //向下
                shot = new Shot(getX()+20,getY()+60,2);
                break;
            case 3 : //向左
                shot = new Shot(getX(),getY()+20,3);
                break;
        }
        getBullet().add(shot);
        setBulletSize(getBullet().size());
        //启动shot线程
        new Thread(shot).start();
    }
}
