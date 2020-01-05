package com.mahjong.baseclass;

import java.util.Arrays;
import java.util.Comparator;

public class MahjongDeck {
    Mahjong[] mahjongs;
    Mahjong[] pMahjongs;
    int mahjongsNum = 0;
    int pMahjongsNum = 0;
    public MahjongDeck() {
        mahjongs=new Mahjong[14];
        pMahjongs=new Mahjong[16];
    }
    public void appendMahjong(Mahjong m){
        //将麻将M加入到牌组中
        mahjongs[mahjongsNum]=m;
        mahjongsNum++;
        Arrays.sort(mahjongs,0,mahjongsNum,m);
    }
    public int countMahjongM(Mahjong m){
        //统计牌组中与麻将m一样的牌的数量
        int r=0;
        for(int i=0;i<mahjongsNum;i++){
            if (m.equals(mahjongs[i])) {
                r++;
            }
        }
        return r;
    }
    public boolean inDeck(Mahjong m){
        //牌组中是否有麻将m
        boolean r=false;
        for(int i=0;i<mahjongsNum;i++){
            if (m.equals(mahjongs[i])) {
                r = true;
                break;
            }
        }
        return r;
    }

    public boolean enablePeng(Mahjong m){
        //当前牌组能否碰麻将m
        return this.countMahjongM(m)>=2;
    }
    public boolean enableEat(Mahjong m){
        //当前牌组能否吃麻将m
        if(m.getMahjongType()!=MahjongType.Feng){
            Mahjong m01=new Mahjong(m.getMahjongType(),m.getMahjongValue()-1);
            Mahjong m02=new Mahjong(m.getMahjongType(),m.getMahjongValue()-2);
            Mahjong m11=new Mahjong(m.getMahjongType(),m.getMahjongValue()+1);
            Mahjong m12=new Mahjong(m.getMahjongType(),m.getMahjongValue()+2);
            if(m01.isEffectMahjong()&&m02.isEffectMahjong()&&this.inDeck(m01)&&this.inDeck(m02))return true;
            if(m01.isEffectMahjong()&&m11.isEffectMahjong()&&this.inDeck(m01)&&this.inDeck(m11))return true;
            return m11.isEffectMahjong() && m12.isEffectMahjong() && this.inDeck(m11) && this.inDeck(m12);
        }
        Mahjong m01=new Mahjong(m.getMahjongType(),(m.getMahjongValue()+1)%4);
        Mahjong m02=new Mahjong(m.getMahjongType(),(m.getMahjongValue()+2)%4);
        Mahjong m03=new Mahjong(m.getMahjongType(),(m.getMahjongValue()+3)%4);
        int count=0;
        if(this.inDeck(m01))count++;
        if(this.inDeck(m02))count++;
        if(this.inDeck(m03))count++;
        return count>=2;
    }
    public Mahjong[] getpMahjongs() {
        return pMahjongs;
    }

    public void setpMahjongs(Mahjong[] pMahjongs) {
        this.pMahjongs = pMahjongs;
    }
    public int getpMahjongsNum() {
        return pMahjongsNum;
    }
    public void setpMahjongsNum(int pMahjongsNum) {
        this.pMahjongsNum = pMahjongsNum;
    }
    public Mahjong[] getMahjongs() {
        return mahjongs;
    }
    public void setMahjongs(Mahjong[] mahjongs) {
        this.mahjongs = mahjongs;
    }
    public int getMahjongsNum() {
        return mahjongsNum;
    }

    public void setMahjongsNum(int mahjongsNum) {
        this.mahjongsNum = mahjongsNum;
    }
    public  void printDeck(){
        int i;
        for(i=0;i<mahjongsNum-1;i++)
            System.out.print(mahjongs[i].toString()+" ");
        System.out.println(mahjongs[i].toString());
    }
}
