package com.wct.TankWar03;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * @author WenCT
 */
//为了让panel不停重绘子弹 ， 实现runnable 接口
public class MyPanel extends JPanel implements KeyListener,Runnable{
    //定义坦克
    Hero hero = null;
    Vector<EnemyTank> Tanks = new Vector<EnemyTank>();
    int TanksSize = 3;
    public MyPanel(){
        hero = new Hero(150,300,0, 5);
        hero.setSpeed(7);
        Map<Locate,Integer> map = new HashMap<>();
        for(int i = 0 ;i <TanksSize ;i++){
            EnemyTank enemyTank = new EnemyTank(100*(i+1),100,2, 3);
            Tanks.add(enemyTank);
            Thread thread = new Thread(enemyTank);
            thread.start();
        }
    }

    @Override
    public void run() {
        while(true){
            //判断是否击中了敌人
            for(int i = 0 ; i < hero.getBulletSize(); i++){
                if(hero.getBullet().get(i).isLive()){
                    for(int j = 0 ; j < Tanks.size() ; j++){
                            EnemyTank enemyTank = Tanks.get(j);
                            if(enemyTank.isLive())
                            hitEnemy(hero.getBullet().get(i),enemyTank);
                    }
                }
            }
            //判断英雄是否被击中
            for(int i = 0; i<Tanks.size(); i++){
                if(Tanks.get(i).isLive()){
                for(int j = 0; j<Tanks.get(i).getBulletSize();j++){
                          Shot s = Tanks.get(i).getBullet().get(j);
                          if(hero.isLive()) hitEnemy(s,hero);
                          Tanks.get(i).removeBullet();
                }
                }
            }
            hero.removeBullet();
            this.removeTanks();
            this.repaint();
            if(!hero.isLive()){
                System.out.println(false);
                break;
            }
            if(Tanks.size() == 0){
                System.out.println(true);
                break;
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);
        if(hero.isLive()) drawTank(hero.getX(),hero.getY(),g,hero.getDirect(),1);
        for(int i = 0 ;i<Tanks.size();i++){
            if(Tanks.get(i).isLive())
            drawTank(Tanks.get(i).getX(),Tanks.get(i).getY(),g,Tanks.get(i).getDirect(), 0);
            for(int j = 0 ; j < Tanks.get(i).getBulletSize(); j++){
                if(Tanks.get(i).getBullet().get(j).isLive())
                drawBullet(Tanks.get(i).getBullet().get(j).getX(),
                        Tanks.get(i).getBullet().get(j).getY(),g,0);
            }

        }
        for(int i = 0 ; i < hero.getBulletSize();i++){
            if(hero.getBullet().get(i).isLive())
                drawBullet(hero.getBullet().get(i).getX(),hero.getBullet().get(i).getY(),g,1);
        }

    }
    public void removeTanks(){
        for(int i = 0 ; i < Tanks.size(); i++){
            if(!Tanks.get(i).isLive()){
                Tanks.remove(i);
            }
        }
        TanksSize = Tanks.size();
    }
    public static void hitEnemy(Shot s, Tank tank2){
                  if(tank2.area.getMap().containsKey(new Locate(s.getX(),s.getY()))){
                      tank2.blood.reduce(1);
                      s.setLive(false);
                      if(tank2.blood.getBlood()<=0){
                      tank2.setLive(false);
                      tank2.area.getMap().clear();
                      }
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
              case 1 :  //表示向右
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
          g.fillOval(x,y, Shot.diameter, Shot.diameter);
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    //处理wdsa键按下的情
    @Override
    public void keyPressed(KeyEvent e) {
           if(e.getKeyCode() == KeyEvent.VK_W){
               this.hero.setDirect(0);
               if(hero.getY()>0) hero.moveUp();
           }
           else if(e.getKeyCode() == KeyEvent.VK_D){
               this.hero.setDirect(1);
               if(hero.getX()+75<1000) hero.moveRight();
           }
           else if(e.getKeyCode() == KeyEvent.VK_S){
               this.hero.setDirect(2);
               if(hero.getY()+100<750) hero.moveDown();
           }
           else if(e.getKeyCode() == KeyEvent.VK_A){
               this.hero.setDirect(3);
               if(hero.getX()>0) hero.moveLeft();
           }
           else if(e.getKeyCode() == KeyEvent.VK_J){
               hero.target();
               }
           hero.area.getMap().clear();
           hero.area.fillMap(hero.getX(),hero.getY(),hero.getDirect());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
