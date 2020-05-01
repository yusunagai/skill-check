package q003;

import q002.Q002;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Q003 集計と並べ替え
 *
 * 以下のデータファイルを読み込んで、出現する単語ごとに数をカウントし、アルファベット辞書順に並び変えて出力してください。
 * resources/q003/data.txt
 * 単語の条件は以下となります
 * - "I"以外は全て小文字で扱う（"My"と"my"は同じく"my"として扱う）
 * - 単数形と複数形のように少しでも文字列が異れば別単語として扱う（"dream"と"dreams"は別単語）
 * - アポストロフィーやハイフン付の単語は1単語として扱う（"isn't"や"dead-end"）
 *
 * 出力形式:単語=数
 *
[出力イメージ]
（省略）
highest=1
I=3
if=2
ignorance=1
（省略）

 * 参考
 * http://eikaiwa.dmm.com/blog/4690/
 */
public class Q003 {
    private static final String regex = "\\.|;|,";
    // 実装部
    public static void main(String[] args) throws IOException {

        String streamData = convertInputStreamToString(openDataFile()).replaceAll(regex,"");
        String[] listData = streamData.toLowerCase() .split(" ");
        Arrays.sort(listData);

        String str = "";
        Integer num = 1;
        for (String listDatum : listData) {
            if(str.equals("")){
                str = listDatum;
            }
            else if(str.equals(listDatum)){
                num++;
            }
            else {
                System.out.println(str + "=" + String.valueOf(num));
                str = listDatum;
                num=1;
            }

        }
    }

    static String convertInputStreamToString(InputStream is) throws IOException {
        InputStreamReader reader = new InputStreamReader(is);
        StringBuilder builder = new StringBuilder();
        char[] buf = new char[1024];
        int numRead;
        while (0 <= (numRead = reader.read(buf))) {
            builder.append(buf, 0, numRead);
        }
        return builder.toString();
    }

    /**
     * データファイルを開く
     * resources/q003/data.txt
     */
    private static InputStream openDataFile() {
        return Q003.class.getResourceAsStream("data.txt");
    }
}
// 完成までの時間: xx時間 xx分
//17:07開始 18:31 中断、