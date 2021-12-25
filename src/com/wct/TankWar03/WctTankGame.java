package com.wct.TankWar03;

import javax.swing.*;

/**
 * @author WenCT
 */
public class WctTankGame extends JFrame {

    MyPanel mp = null;
    public static void main(String[] args) {
        WctTankGame wctTankGame01 = new WctTankGame();
    }
    public WctTankGame(){
        mp = new MyPanel();
        this.add(mp);
        Thread thread = new Thread(mp);
        thread.start();
        this.setSize(1000,750);
        this.addKeyListener(mp); //让JFrame监a听mp的键盘事件
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
