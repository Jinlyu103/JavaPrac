package nowcode.huaWeiPrac.hj13_sentenceRev;

import java.util.Scanner;

public class Main {
    /**
     * 句子逆序
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String sentence = sc.nextLine();
            String[] words = sentence.split(" ");
            for (int i = words.length-1; i>=0; i--){
                if (i != 0){
                    System.out.print(words[i] + " ");
                } else {
                    System.out.println(words[i]);
                }
            }
        }
        sc.close();
    }
}
