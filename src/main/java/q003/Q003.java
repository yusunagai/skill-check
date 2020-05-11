package q003;
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
    //除外文字
    private static final String regex = "\\.|;|,";
    //改行文字
    private static final String nc = "\\r\\n";
    //分割文字
    private static final String split = " +";
    // 実装部
    public static void main(String[] args) throws IOException {
        //除外文字削除、改行を空白に一旦変換
        String streamData = convertInputStreamToString(openDataFile()).replaceAll(regex,"").replaceAll(nc," ");
        //スペースでストリームを区切る、かつ小文字化
        String[] listData = streamData.toLowerCase().split(split);
        //配列をソート
        Arrays.sort(listData);

        String str = "";
        Integer num = 1;
        //配列内の直前の要素を比較。同じ要素ならカウントプラス
        for (String listDatum : listData) {
            if(str.equals("")){
                str = listDatum;
            }
            else if(str.equals(listDatum)){
                num++;
            }
            else {
                printString(str,num);
                str = listDatum;
                num=1;
            }
        }
        //最後の内容を出力
        printString(str,num);
    }
    //ワード＋カウント出力
    static void printString(String str, Integer num){
        //iのときだけ大文字変換
        if(str.equals("i")){
            str="I";
        }
        //単独ハイフンを除外
        if(!str.equals("–")){
            System.out.println(str + "=" + String.valueOf(num));
        }
    }

    //Streamに値を設定
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
// 完成までの時間: 01時間 59分
// 1日目　17:07開始 18:31 中断
// 集計用独自クラスを作成してそこに格納せず
// ハイフンの扱いに苦慮。大文字ハイフンを除外すれば一括でいけるが、小文字かして単独の場合のみ除去