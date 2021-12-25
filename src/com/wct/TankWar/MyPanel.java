package com.wct.TankWar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * @author WenCT
 */
//为了让panel不停重绘子弹 ， 实现runnable 接口
public class MyPanel extends JPanel implements KeyListener,Runnable{
    //定义坦克
    Hero hero = null;
    Vector<Tank> Tanks = new Vector<Tank>();
    int TanksSize = 3;
    public MyPanel(){
        hero = new Hero(150,300,0);
        hero.setSpeed(6);
        for(int i = 0 ;i <TanksSize ;i++){
            Tank tank = new Tank(100*(i+1),100,2);
            Tanks.add(tank);
            Thread thread = new Thread(tank);
            thread.start();
        }
    }

    @Override
    public void run() {
        while(true){
            repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);
        drawTank(hero.getX(),hero.getY(),g,hero.getDirect(),1);
        for(int i = 0 ;i<Tanks.size();i++){
            drawTank(Tanks.get(i).getX(),Tanks.get(i).getY(),g,Tanks.get(i).getDirect(), 0);
            for(int j = 0 ; j < Tanks.get(i).getBulletSize(); j++){
                drawBullet(Tanks.get(i).getBullet().get(j).getX(),
                        Tanks.get(i).getBullet().get(j).getY(),g,0);
            }
        }
        for(int i = 0 ; i < hero.getBulletSize();i++){
            if(hero.getBullet().get(i).isLive())
                drawBullet(hero.getBullet().get(i).getX(),hero.getBullet().get(i).getY(),g,1);
        }

    }

    /**
     *
     * @param x 坦克左上角的x坐标
     * @param y 坦克左上角的y坐标
     * @param g 画笔
     * @param direct  坦克方向
     * @param type  坦克类型
     */
    public void drawTank(int x , int y , Graphics g , int direct , int type){
          //根据不同类型的坦克，设置不同颜色
           switch (type){
               case 0: //敌人坦克
                     g.setColor(Color.cyan);
                     break;
               case 1: //玩家的坦克
                     g.setColor(Color.yellow);
                     break;
           }

           //根据坦克方向绘制坦克
          switch (direct){
              case 0 :  //表示向上
                  g.fill3DRect( x, y,10,60,false); //画出坦克左边的轮子
                  g.fill3DRect( x + 30, y,10,60,false); //画出坦克右边的轮子
                  g.fill3DRect(x+10, y+10 , 20 ,40,false); //画出坦克的盖子
                  g.fillOval(x+10,y+20,20,20);//画出圆形盖子
                  g.drawLine(x+20,y,x+20,y+30);//画出炮筒
                  break;
              case 1 :  //表示向下
                  g.fill3DRect( x, y,60,10,false); //画出坦克左边的轮子
                  g.fill3DRect( x, y+30,60,10,false); //画出坦克右边的轮子
                  g.fill3DRect(x+10, y+10 , 40 ,20,false); //画出坦克的盖子
                  g.fillOval(x+20,y+10,20,20);//画出圆形盖子
                  g.drawLine(x+60,y+20,x+30,y+20);//画出炮筒
                  break;
              case 2 : //表示向下
                  g.fill3DRect( x, y,10,60,false); //画出坦克左边的轮子
                  g.fill3DRect( x + 30, y,10,60,false); //画出坦克右边的轮子
                  g.fill3DRect(x+10, y+10 , 20 ,40,false); //画出坦克的盖子
                  g.fillOval(x+10,y+20,20,20);//画出圆形盖子
                  g.drawLine(x+20,y+30,x+20,y+60);//画出炮筒
                  break;
              case 3 : //表示向左
                  g.fill3DRect( x, y,60,10,false); //画出坦克左边的轮子
                  g.fill3DRect( x, y+30,60,10,false); //画出坦克右边的轮子
                  g.fill3DRect(x+10, y+10 , 40 ,20,false); //画出坦克的盖子
                  g.fillOval(x+20,y+10,20,20);//画出圆形盖子
                  g.drawLine(x,y+20,x+30,y+20);//画出炮筒
                  break;
              default:
                  System.out.println("Without type!");
          }
    }

    public void drawBullet(int x , int y , Graphics g, int type){
        switch (type){
            case 0: //敌人坦克
                g.setColor(Color.cyan);
                break;
            case 1: //玩家的坦克
                g.setColor(Color.yellow);
                break;
        }
          g.fillOval(x,y,5,5);
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    //处理wdsa键按下的情
    @Override
    public void keyPressed(KeyEvent e) {
           if(e.getKeyCode() == KeyEvent.VK_W){
               this.hero.setDirect(0);
               hero.moveUp();
           }
           else if(e.getKeyCode() == KeyEvent.VK_D){
               this.hero.setDirect(1);
               hero.moveRight();
           }
           else if(e.getKeyCode() == KeyEvent.VK_S){
               this.hero.setDirect(2);
               hero.moveDown();
           }
           else if(e.getKeyCode() == KeyEvent.VK_A){
               this.hero.setDirect(3);
               hero.moveLeft();
           }
           else if(e.getKeyCode() == KeyEvent.VK_J){
               hero.removeBullet();
               hero.target();
               }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
