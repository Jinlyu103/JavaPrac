package Alibaba2021.t2;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> res = new ArrayList<>();

        while (sc.hasNext()){
            int n = Integer.parseInt(sc.nextLine()); //单词个数
            if (n == 0){
                break;
            }

            String[] strings = new String[n]; //加密前的单词

            for (int i = 0; i<n; i++){
                strings[i] = sc.nextLine();
            }
            String word = sc.nextLine(); //加密后的句子
            String text = solve(word, strings); //求解明文
            res.add(text);
        }

        for (String s:res){
            System.out.println(s);
        }
    }

    public static String solve(String word, String[] strings){
        //按照单词长度从小到大对strings排序
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()){
                    return 0;
                }
                if (o1.length()>o2.length()){
                    return 1;
                }
                return -1;
            }
        });

        String[] words = (word.substring(0, word.length()-1)).split(" ");
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()){
                    return 0;
                }
                if (o1.length()>o2.length()){
                    return 1;
                }
                return -1;
            }
        });

        //两个字符对应关系，key为密文中的字符，value为明文中的字符
        Map<Character, Character> hmap = new HashMap<>();
        StringBuffer res = new StringBuffer();
        int ptr_s = 0; // 指向明文单词数组的指针
        int ptr_w = 0; // 指向密文句子单词的指针
        while (ptr_s < strings.length && ptr_w<words.length){
            if (strings[ptr_s].length() == words[ptr_w].length()){
                for (int i = 0; i < strings[ptr_s].length(); i++){
                    char cur_s = strings[ptr_s].charAt(i);
                    char cur_w = words[ptr_w].charAt(i);
                    if (hmap.containsKey(cur_w)){
                        if (hmap.get(cur_w) != cur_s){
                            return "-";
                        }
                    } else {
                        hmap.put(cur_w, cur_s); //建立字母配对关系
                    }
                }
                ptr_s ++;
                ptr_w ++;
            } else if (strings[ptr_s].length() > words[ptr_w].length()){
                ptr_w ++;
            } else {
                ptr_s ++;
            }
        }

        for (int i = 0; i<words.length; i++){
            for (int j = 0; j<words[i].length(); j++){
                char cur = words[i].charAt(j);
                if (hmap.containsKey(cur)){
                    res.append(hmap.get(cur));
                }
            }
            if (i!=words.length){
                res.append(" ");
            }
        }
        res.append(".");
        return res.toString();
    }
}
