package nowcode.huaWeiPrac.hj14_findLongestPath;


import java.util.*;

public class Main {
    /**
     * 给定n个字符串，请对n个字符串按照字典序排列
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int n = sc.nextInt();
            List<String> words = new ArrayList<>();
            for (int i = 0; i<n; i++){
                String str = sc.next();
                words.add(str);
            }

            Collections.sort(words);
            for (String w:words){
                System.out.println(w);
            }
        }
        sc.close();
    }
}
