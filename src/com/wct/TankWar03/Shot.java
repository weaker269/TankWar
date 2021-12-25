package com.wct.TankWar03;

/**
 * @author WenCT
 */
public class Shot implements Runnable{
    private int x; //子弹横坐标
    private int y; //子弹纵坐标
    private int direct; //子弹方向
    private int speed = 5; //子弹速度
    private boolean isLive = true; //子弹是否存在
    public static int diameter = 4;//子弹直径
    private int[] dx = {0,speed,0,-speed};
    private int[] dy = {-speed,0,speed,0};
    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
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

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
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
    public void move(){
        x += dx[direct];
        y += dy[direct];
    }

    @Override
    public void run() {
        while (isLive) {
            move();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //当子弹达到边界
            if((x < 0 || x > 1000 || y < 0 || y >= 750)||(!isLive)){
                isLive = false;
                break;
            }
        }
    }
}
