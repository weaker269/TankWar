package com.wct.TankWar02;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WenCT
 */
public class Area {
    private Map<Locate,Integer> map = new HashMap<>();
    private boolean isLive = true;

    public Area(int x , int y , int direct) {
        this.fillMap(x,y,direct);
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public Map<Locate, Integer> getMap() {
        return map;
    }

    private void fillMap(int x , int y , int direct){
        switch (direct){
            case 0 :
            case 2 :
                for(int i = 0 ; i <= 10 ; i++)  //左方轮子
                    for(int j = 0 ; j <= 60 ; j++)
                        map.put(new Locate(x+i,y+j),1);
                for(int i = 30 ; i <= 40 ;i++) //右方轮子
                    for(int j = 0 ; j <= 60 ; j++)
                        map.put(new Locate(x+i,y+j),1);
                for(int i = 10 ; i <= 30 ;i++) //盖子
                    for(int j = 10 ; j <= 50 ;j++)
                        map.put(new Locate(x+i,y+j),1);
                break;
            case 1 :
            case 3 :
                for(int i = 0 ; i <= 60 ; i++ ) //上方轮子
                    for(int j = 0 ; j <= 10 ; j++)
                        map.put(new Locate(x+i,y+j),1);
                for(int i = 0 ; i <= 60 ; i++ ) //下方轮子
                    for(int j = 30 ; j <= 40 ; j++)
                        map.put(new Locate(x+i,y+j),1);
                for(int i = 10 ; i <= 50 ; i++ ) //上方轮子
                    for(int j = 10 ; j <= 30 ; j++)
                        map.put(new Locate(x+i,y+j),1);
                break;
        }
    }
}
