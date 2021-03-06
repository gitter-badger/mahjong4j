package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.tile.MahjongTileType;

/**
 * 混一色判定クラス
 * 萬子、索子、筒子のどれか一種と、字牌のみで構成される場合成立
 *
 * @author yu1ro
 */
public class HonitsuResolver implements NormalYakuResolver {
    final int HAN = MahjongYakuEnum.HONITSU.getHan();
    private int colorCount;

    public HonitsuResolver(MahjongHands hands) {

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

    /*
     * 通常の形用の混一色チェッカー
     */
    public boolean isHonitsu(MahjongTile[] shuntsu, MahjongTile[] kotsu,
                             MahjongTile janto) {
        // 初期化する。これが1でのみtrueを返す
        colorCount = 0;
        boolean hasJihai = false;

        //字牌があるかチェック
        //雀頭から
        if (janto.getNumber() == 0) {
            hasJihai = true;
        }

        if (!hasJihai) {
            for (int i = 0; i < kotsu.length && kotsu[i] != null; i++) {
                if (kotsu[i].getNumber() == 0) {
                    hasJihai = true;
                    break;
                }
            }
        }

        manzuCheck(shuntsu, kotsu, janto);
        pinzuCheck(shuntsu, kotsu, janto);
        sozuCheck(shuntsu, kotsu, janto);

        return colorCount == 1 && hasJihai;
    }

    /*
     * 七対子用の混一色チェッカー
     */
    public boolean isHonitsu(MahjongTile[] toitsu) {
        // 初期化する。これが1ならtrue
        colorCount = 0;
        boolean hasJihai = false;
        /*
         * 字牌が含まれるかチェック
         * 最後の方に字牌が来ると思うので後ろからチェック
         */
        for (int i = toitsu.length - 1; i >= 0; i--) {
            if (toitsu[i].getNumber() == 0) {
                hasJihai = true;
                break;
            }
        }

        manzuCheck(toitsu);
        pinzuCheck(toitsu);
        sozuCheck(toitsu);

        return colorCount == 1 && hasJihai;
    }

    /*
     * ここからそれぞれの色が含まれるかをチェックするメソッド
     * 七対子用か通常用かは引数を見て判断して下さい。
     */

    private void manzuCheck(MahjongTile[] shuntsu, MahjongTile[] kotsu,
                            MahjongTile janto) {
        boolean flag = true;
        if (janto.getType() == MahjongTileType.MANZU) {
            colorCount++;
            flag = false;
        } else if (flag) {
            for (int i = 0; i < shuntsu.length && shuntsu[i] != null; i++) {
                if (shuntsu[i].getType() == MahjongTileType.MANZU) {
                    colorCount++;
                    flag = false;
                    break;
                }
            }
            if (flag) {
                for (int i = 0; i < kotsu.length && kotsu[i] != null; i++) {
                    if (kotsu[i].getType() == MahjongTileType.MANZU) {
                        colorCount++;
                        flag = false;
                        break;
                    }
                }
            }

        }
    }

    private void manzuCheck(MahjongTile[] toitsu) {
        for (int i = 0; i < toitsu.length && toitsu[i] != null; i++) {
            if (toitsu[i].getType() == MahjongTileType.MANZU) {
                colorCount++;
                break;
            }
        }

    }

    private void pinzuCheck(MahjongTile[] shuntsu, MahjongTile[] kotsu,
                            MahjongTile janto) {
        boolean flag = true;
        if (janto.getType() == MahjongTileType.PINZU) {
            colorCount++;
            flag = false;
        } else if (flag) {
            for (int i = 0; i < shuntsu.length && shuntsu[i] != null; i++) {
                if (shuntsu[i].getType() == MahjongTileType.PINZU) {
                    colorCount++;
                    flag = false;
                    break;
                }
            }
            if (flag) {
                for (int i = 0; i < kotsu.length && kotsu[i] != null; i++) {
                    if (kotsu[i].getType() == MahjongTileType.PINZU) {
                        colorCount++;
                        flag = false;
                        break;
                    }
                }
            }
        }
    }

    private void pinzuCheck(MahjongTile[] toitsu) {
        for (MahjongTile aToitsu : toitsu) {
            if (aToitsu.getType() == MahjongTileType.PINZU) {
                colorCount++;
                break;
            }
        }

    }

    private void sozuCheck(MahjongTile[] shuntsu, MahjongTile[] kotsu,
                           MahjongTile janto) {
        boolean flag = true;
        if (janto.getType() == MahjongTileType.SOHZU) {
            colorCount++;
            flag = false;
        } else if (flag) {
            for (int i = 0; i < shuntsu.length && shuntsu[i] != null; i++) {
                if (shuntsu[i].getType() == MahjongTileType.SOHZU) {
                    colorCount++;
                    flag = false;
                    break;
                }
            }
            if (flag) {
                for (int i = 0; i < kotsu.length && kotsu[i] != null; i++) {
                    if (kotsu[i].getType() == MahjongTileType.SOHZU) {
                        colorCount++;
                        flag = false;
                        break;
                    }
                }

            }
        }
    }

    private void sozuCheck(MahjongTile[] toitsu) {
        for (int i = 0; i < toitsu.length && toitsu[i] != null; i++) {
            if (toitsu[i].getType() == MahjongTileType.SOHZU) {
                colorCount++;
                break;
            }
        }
    }
}
