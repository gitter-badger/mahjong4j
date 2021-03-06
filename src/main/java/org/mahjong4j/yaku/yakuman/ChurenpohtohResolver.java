package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.tile.MahjongTileType;

import static org.mahjong4j.yaku.yakuman.MahjongYakumanEnum.CHURENPOHTO;

/**
 * @author yu1ro
 *         九蓮宝燈判定クラス
 */
public class ChurenpohtohResolver implements YakumanResolver {

    final int[] churenManzu = {
        3, 1, 1, 1, 1, 1, 1, 1, 3,
        0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0
    };
    final int[] churenPinzu = {
        0, 0, 0, 0, 0, 0, 0, 0, 0,
        3, 1, 1, 1, 1, 1, 1, 1, 3,
        0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0
    };

    final int[] churenSohzu = {
        0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0,
        3, 1, 1, 1, 1, 1, 1, 1, 3,
        0, 0, 0, 0,
        0, 0, 0
    };


    MahjongTileType churenType;
    private int[] hands = new int[34];

    public ChurenpohtohResolver(MahjongHands hands) {

    }

    public boolean isChuren() {

        //どのタイプが含まれているか調べる
        for (int i = 0; i < hands.length; i++) {
            if (hands[i] > 0) {
                churenType = MahjongTile.valueOf(i).getType();
                break;
            }
        }

        //タイプ毎に調べる
        int count = 0;
        switch (churenType) {
            case MANZU:
                for (int i = 0; i < hands.length; i++) {
                    hands[i] -= churenManzu[i];

                    if (hands[i] < 0 || hands[i] > 1) {
                        return false;
                    }
                    if (i < 9 && hands[i] == 1) {
                        count++;
                    }

                }
                break;
            case PINZU:
                for (int i = 0; i < hands.length; i++) {
                    hands[i] -= churenPinzu[i];

                    if (hands[i] < 0 || hands[i] > 1) {
                        return false;
                    }
                    if (i > 8 && i < 18 && hands[i] == 1) {
                        count++;
                    }

                }
                break;
            case SOHZU:
                for (int i = 0; i < hands.length; i++) {
                    hands[i] -= churenSohzu[i];

                    if (hands[i] < 0 || hands[i] > 1) {
                        return false;
                    }
                    if (i > 17 && i < 27 && hands[i] == 1) {
                        count++;
                    }

                }
                break;
            default:
                return false;
        }
        return count == 1;
    }

    public MahjongYakumanEnum getYakuman() {
        return CHURENPOHTO;
    }

    public boolean isMatch() {
        return false;
    }
}
