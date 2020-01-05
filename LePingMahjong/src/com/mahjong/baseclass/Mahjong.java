package com.mahjong.baseclass;

import java.util.Comparator;
import java.util.Objects;

public class Mahjong implements Comparable<Mahjong>, Comparator<Mahjong> {
    MahjongType mahjongType;
    int mahjongValue;
    public Mahjong(int i){
        switch (i/10){
            case 0:mahjongType=MahjongType.Bing;break;
            case 1:mahjongType=MahjongType.Tong;break;
            case 2:mahjongType=MahjongType.Wang;break;
            case 3:mahjongType=MahjongType.ZFB;break;
            case 4:mahjongType=MahjongType.Feng;break;
        }
        mahjongValue=i%10;
    }
    public Mahjong(MahjongType mahjongType, int i) {
        this.mahjongType=mahjongType;
        this.mahjongValue=i;
    }


    @Override
    public int compare(Mahjong mahjong, Mahjong t1) {
        return mahjong.getFinalValue()-t1.getFinalValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mahjong mahjong = (Mahjong) o;
        return mahjongValue == mahjong.mahjongValue &&
                mahjongType == mahjong.mahjongType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mahjongType, mahjongValue);
    }

    public boolean isEffectMahjong(){
        switch (mahjongType){
            case Bing:
            case Tong:
            case Wang:
                return mahjongValue < 9 && mahjongValue >= 0;
            case ZFB:
                return mahjongValue < 3 && mahjongValue >= 0;
            case Feng:
                return mahjongValue < 4 && mahjongValue >= 0;
        }
        return false;
    }
    public boolean isAdjacent(Mahjong m){
        if(m.mahjongType!=this.mahjongType)return false;
        if(this.mahjongType==MahjongType.Bing||this.mahjongType==MahjongType.Tong||this.mahjongType==MahjongType.Wang){
            return Math.abs(this.mahjongValue-m.getMahjongValue())<3;
        }
        return this.mahjongValue!=m.getMahjongValue();
    }
    public int getFinalValue(){
        return mahjongType.ordinal()*10+mahjongValue;
    }
    public MahjongType getMahjongType() {
        return mahjongType;
    }

    public void setMahjongType(MahjongType mahjongType) {
        this.mahjongType = mahjongType;
    }

    public int getMahjongValue() {
        return mahjongValue;
    }

    public void setMahjongValue(int mahjongValue) {
        this.mahjongValue = mahjongValue;
    }

    @Override
    public String toString() {
        return ""+(this.mahjongValue+1)+this.getMahjongType();
    }


    @Override
    public int compareTo(Mahjong mahjong) {
        return this.getFinalValue()-mahjong.getFinalValue();
    }
}
