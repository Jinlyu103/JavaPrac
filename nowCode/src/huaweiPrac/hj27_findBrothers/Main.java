package huaweiPrac.hj27_findBrothers;

import java.util.*;

public class Main {
    /**
     * 实现一个可存储若干单词的字典
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            String[] dict = new String[n];

            for (int i = 0; i < n; i++) {
                String tmp = sc.next();

                dict[i] = tmp;
            }

            String w = sc.next();
            int k = sc.nextInt();
            List<String> res = new ArrayList<>(); //存放兄弟节点
            for (int i = 0; i < dict.length; i++) {
                if (isBrother(dict[i], w)) {
                    res.add(dict[i]);
                }
            }

            System.out.println(res.size());

            Collections.sort(res);
            if (k <= res.size()) {
                System.out.println(res.get(k - 1));
            }
        }
    }

    public static boolean isBrother(String dictWord, String word){
        //两个单词一样或者长度不一样，不是兄弟
        if (dictWord.length() != word.length() || dictWord.equals(word)){
            return false;
        }
        //长度相等并且不是同一个单词

        char[] dw = dictWord.toCharArray();
        Arrays.sort(dw);

        char[] w = word.toCharArray();
        Arrays.sort(w);

        boolean flag = true;
        for (int i = 0; i<word.length(); i++){
            if (dw[i] != w[i]){
                flag = false;
                break;
            }
        }
        return flag;
    }
}
