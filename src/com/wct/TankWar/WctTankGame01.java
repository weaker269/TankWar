package com.wct.TankWar;

import javax.swing.*;

/**
 * @author WenCT
 */
public class WctTankGame01 extends JFrame {

    MyPanel mp = null;
    public static void main(String[] args) {
        WctTankGame01 wctTankGame01 = new WctTankGame01();
    }
    public WctTankGame01(){
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
