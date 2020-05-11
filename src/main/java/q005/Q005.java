package q005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Q005 データクラスと様々な集計
 *
 * 以下のファイルを読み込んで、WorkDataクラスのインスタンスを作成してください。
 * resources/q005/data.txt
 * (先頭行はタイトルなので読み取りをスキップする)
 *
 * 読み込んだデータを以下で集計して出力してください。
 * (1) 役職別の合計作業時間
 * (2) Pコード別の合計作業時間
 * (3) 社員番号別の合計作業時間
 * 上記項目内での出力順は問いません。
 *
 * 作業時間は "xx時間xx分" の形式にしてください。
 * また、WorkDataクラスは自由に修正してください。
 *
[出力イメージ]
部長: xx時間xx分
課長: xx時間xx分
一般: xx時間xx分
Z-7-31100: xx時間xx分
I-7-31100: xx時間xx分
T-7-30002: xx時間xx分
（省略）
194033: xx時間xx分
195052: xx時間xx分
195066: xx時間xx分
（省略）
 */
public class Q005 {
    // 実装部
    public static void main(String[] args) throws IOException {
        //1行ずつ読み込む
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(Q005.class.getResourceAsStream("data.txt"))
        );
        //読み込んだデータに格納する
        List times = new ArrayList();
        WorkData data = new WorkData();
        String line;
        while((line = reader.readLine()) != null){
            String[] read = line.split(",");
            if(!read[0].equals("社員番号")){
                data = new WorkData();
                data.setNumber(read[0]);
                data.setDepartment(read[1]);
                data.setPosition(read[2]);
                data.setpCode(read[3]);
                data.setWorkTime(Integer.parseInt(read[4]));
                times.add(data);
            }
        }
        //集計用Map
        Map<String, Integer> positionMap = (Map<String, Integer>) times.stream().collect(Collectors.groupingBy(WorkData::getPosition,Collectors.summingInt(WorkData::getWorkTime)));
        Map<String, Integer> pCodeMap = (Map<String, Integer>) times.stream().collect(Collectors.groupingBy(WorkData::getpCode,Collectors.summingInt(WorkData::getWorkTime)));
        Map<String, Integer> numberMap = (Map<String, Integer>) times.stream().collect(Collectors.groupingBy(WorkData::getNumber,Collectors.summingInt(WorkData::getWorkTime)));

        //結果を出力
        printMap(positionMap);
        printMap(pCodeMap);
        printMap(numberMap);
    }

    //Map出力
    static void printMap(Map<String, Integer> map){
        for (String key : map.keySet()) {
            Integer hour = map.get(key)/60;
            System.out.println(key + "：　" + String.valueOf(hour) + "時間" + String.valueOf(map.get(key)-hour*60) + "分");
        }
    }
}
// 完成までの時間: 1時間 45分
// 14:15 16:00
//　途中顧客との電話、Slack対応などあり、面倒なのでその分もカウント。
//　検索してHITしたCollectorsの活用で楽に。