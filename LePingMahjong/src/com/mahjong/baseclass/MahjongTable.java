package com.mahjong.baseclass;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MahjongTable {
    Mahjong[] mahjongList;
    int mahjongNum;

    public MahjongTable() {
        mahjongList=new Mahjong[136];
        mahjongNum=136;
    }
    public Mahjong popMahjong(){
        mahjongNum--;
        return mahjongList[mahjongNum];
    }
    public void renewMahjongList(){
        List<Integer> list=new ArrayList<>();
        int size=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<9;j++){
                for(int k=0;k<4;k++){
                    list.add(i * 10 + j);
                }
            }
        }
        for (int j=0;j<3;j++){
            for(int k=0;k<4;k++)
                list.add(30+j);
        }
        for (int j=0;j<4;j++){
            for(int k=0;k<4;k++)
                list.add(40+j);
        }
        Random r=new Random();
        int i;
        while (size<136){
            i=r.nextInt(list.size());
            Mahjong m=new Mahjong(list.get(i));
            mahjongList[size]=m;
            list.remove(i);
            size++;
        }
        mahjongNum=136;
    }

    public Mahjong[] getMahjongList() {
        return mahjongList;
    }

    public void setMahjongList(Mahjong[] mahjongList) {
        this.mahjongList = mahjongList;
    }

    public int getMahjongNum() {
        return mahjongNum;
    }

    public void setMahjongNum(int mahjongNum) {
        this.mahjongNum = mahjongNum;
    }

}
