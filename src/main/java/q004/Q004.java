package q004;

import java.io.IOException;

/**
 * Q004 ソートアルゴリズム
 *
 * ListManagerクラスをnewして、小さい順に並び変えた上でcheckResult()を呼び出してください。
 *
 * 実装イメージ:
 * ListManager data = new ListManager();
 * // TODO 並び換え
 * data.checkResult();
 *
 * - ListManagerクラスを修正してはいけません
 * - ListManagerクラスの dataList を直接変更してはいけません
 * - ListManagerクラスの比較 compare と入れ替え exchange を使って実現してください
 */
public class Q004 {
    // 実装部
    public static void main(String[] args) {
        ListManager data = new ListManager();
        //バブルソート実装
        for (int i = 0; i < data.size()-1; i ++){
            for (int j = data.size()-1; j > i; j --){
                if (data.compare(j-1,j)==1){
                    data.exchange(j-1,j);
                }
            }
        }
        data.checkResult();
    }
}
// 完成までの時間: 00時間 09分
// 13:56 14:05
// Java ソート アルゴリズムでググルと回答が出てきた。下記を参考に実装。
// https://techacademy.jp/magazine/19444