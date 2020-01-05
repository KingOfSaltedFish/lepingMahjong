import com.mahjong.baseclass.MahjongDeck;
import com.mahjong.baseclass.MahjongTable;

public class jsTest{
    public static void main(String[] args){
        MahjongTable mt=new MahjongTable();
        mt.renewMahjongList();
        MahjongDeck md1=new MahjongDeck();
        MahjongDeck md2=new MahjongDeck();
        MahjongDeck md3=new MahjongDeck();
        MahjongDeck md4=new MahjongDeck();
        for(int i=0;i<3;i++){
            for(int j=0;j<4;j++){
                md1.appendMahjong(mt.popMahjong());
            }
            for(int j=0;j<4;j++){
                md2.appendMahjong(mt.popMahjong());
            }
            for(int j=0;j<4;j++){
                md3.appendMahjong(mt.popMahjong());
            }
            for(int j=0;j<4;j++){
                md4.appendMahjong(mt.popMahjong());
            }
        }
        md1.appendMahjong(mt.popMahjong());
        md1.appendMahjong(mt.popMahjong());
        md2.appendMahjong(mt.popMahjong());
        md3.appendMahjong(mt.popMahjong());
        md4.appendMahjong(mt.popMahjong());
        md1.printDeck();
        md2.printDeck();
        md3.printDeck();
        md4.printDeck();
    }
}
