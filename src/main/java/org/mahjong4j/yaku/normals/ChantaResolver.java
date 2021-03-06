package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;

/**
 * チャンタ判定クラス
 * 123の順子と789の順子、および一九字牌の対子と刻子
 * のみで構成された場合成立
 *
 * @author yu1ro
 */
public class ChantaResolver implements NormalYakuResolver {
    final int HAN = MahjongYakuEnum.CHANTA.getHan();
    final int KUISAGARI = MahjongYakuEnum.CHANTA.getKuisagari();

    int[] shuntsuHands = {
        0, 1, 0, 0, 0, 0, 0, 1, 0,
        0, 1, 0, 0, 0, 0, 0, 1, 0,
        0, 1, 0, 0, 0, 0, 0, 1, 0,
        0, 0, 0, 0,
        0, 0, 0
    };
    int[] kotsuHands = {
        1, 0, 0, 0, 0, 0, 0, 0, 1,
        1, 0, 0, 0, 0, 0, 0, 0, 1,
        1, 0, 0, 0, 0, 0, 0, 0, 1,
        1, 1, 1, 1,
        1, 1, 1
    };

    public ChantaResolver(MahjongHands hands) {

    }


    public int getHan() {
        return HAN;
    }

    public boolean isMatch() {
        return false;
    }

    public MahjongYakuEnum getNormalYaku() {
        return null;
    }

    public boolean isChanta(MahjongTile[] shuntsu, MahjongTile[] kotsu, MahjongTile janto) {

        int count = 0;//これが4になれば全部么九牌を含む

        //まずは順子
        for (int i = 0; i < shuntsu.length && shuntsu[i] != null; i++) {
            if (shuntsuHands[shuntsu[i].getCode()] == 1) {
                count++;
            }
        }

        //次に刻子
        for (int i = 0; kotsu[i] != null && i < kotsu.length; i++) {
            if (kotsuHands[kotsu[i].getCode()] == 1) {
                count++;
            }
        }

        //４になってるかと雀頭もチェック
        return count == 4 && kotsuHands[janto.getCode()] == 1;
    }
}
