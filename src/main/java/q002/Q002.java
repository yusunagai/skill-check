package q002;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Q002 並べ替える
 *
 * dataListに "ID,名字" の形式で20個のデータがあります。
 * これをID順に並べて表示するプログラムを記述してください。
 *
 * dataListの定義を変更してはいけません。
 *
 *
[出力結果イメージ]
1,伊藤
2,井上
（省略）
9,清水
10,鈴木
11,高橋
（省略）
20,渡辺
 */
public class Q002 {
    /**
     * データ一覧
     */
    private static final String[] dataList = {
            "8,佐藤",
            "10,鈴木",
            "11,高橋",
            "12,田中",
            "20,渡辺",
            "1,伊藤",
            "18,山本",
            "13,中村",
            "5,小林",
            "3,加藤",
            "19,吉田",
            "17,山田",
            "7,佐々木",
            "16,山口",
            "6,斉藤",
            "15,松本",
            "2,井上",
            "4,木村",
            "14,林",
            "9,清水"
    };
    // 実装部分
    public static void main(String[] args)  {
        String[] listData = dataList;
        StrCompare c = new StrCompare();

        Arrays.sort(listData, c);

        for (String listDatum : listData) {
            System.out.println(listDatum);
        }
    }

    private static class StrCompare implements Comparator {
        public int compare(Object a, Object b) {
            if (a == b) return 0;
            if (a instanceof String
                    && b instanceof String) {

                String[] sa = ((String) a).split(",");
                String[] sb = ((String) b).split(",");

                if (sa[0].length() == sb[0].length()) {
                    return sa[0].compareTo(sb[0]);
                } else if (sa[0].length() < sb[0].length()) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                throw new RuntimeException("Not String");
            }
        }
    }
}
// 完成までの時間: 1時間 11分
// IDE上で実行結果が文字化ける⇒IntellJ周りの設定を見直し、さらにIDEが起動しない問題が発生
// ユーザ名が日本語のため、日本語化のPleiadesを読みに行けなかった。ファイル置き場を変更、パスもかえて解決