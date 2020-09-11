package tencent2021.code1.t3;

import java.util.*;

public class Main {
    /**
     * 字符串次数
     * 给定N个字符串，统计：出现次数前K多和前K少的字符串
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int N = sc.nextInt();
            int K = sc.nextInt();
            String[] strings = new String[N];
            for (int i = 0; i<N; i++){
                strings[i] = sc.next();
            }
            Freq[] topKFreq = topKFrequent(strings,K);  //统计出现频率前k高的字符串
            Freq[] downKFreq= downKFrequent(strings,K); //统计出现频率前K少的字符串

            for (Freq f: topKFreq){
                System.out.println(f.s + " " + f.freq);
            }

            for (Freq f: downKFreq){
                System.out.println(f.s + " " + f.freq);
            }
        }
    }

    public static Freq[] topKFrequent(String[] strings, int k){
        /**
         * 统计每个字符串出现频率，哈希表
         * 用优先队列存放 （元素：出现频率）结构的数组
         */
        //记录元素出现频率的哈希表按照键值字典序排列
        Map<String, Integer> strFreq = new HashMap<>();

        PriorityQueue<Freq> freqs = new PriorityQueue<>(new Comparator<Freq>() {
            @Override
            public int compare(Freq o1, Freq o2) {
                if (o1.freq !=o2.freq)
                    return Integer.compare(o1.freq, o2.freq); //按照出现频率排序
                else
                    return compare(o1.s, o2.s);
            }
            private int compare(String o1, String o2) {
                if (o1.length()==0 && o2.length() == 0){
                    return 0;
                }
                if (o1.length()>0 && o2.length() == 0){
                    return 1;
                }
                if (o1.length()==0 && o2.length()>0){
                    return -1;
                }
                else {
                    if (o1.charAt(0) == o2.charAt(0)){
                        return compare(o1.substring(1), o2.substring(1));
                    }
                    if (o1.charAt(0) > o2.charAt(0)){
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        });

        for (int i = 0; i<strings.length; i++){
            if (strFreq.containsKey(strings[i])){
                strFreq.put(strings[i], strFreq.get(strings[i])+1);
            } else {
                strFreq.put(strings[i],1);
            }
        }

        for (String s: strFreq.keySet()){
            Freq f = new Freq();
            f.s = s;
            f.freq = strFreq.get(s);
            freqs.offer(f);
        }

        Freq[] res = new Freq[k];
        for (int i = 0; i<k; i++){
            res[i] = freqs.poll();
        }
        return res;
    }

    public static Freq[] downKFrequent(String[] strings, int k){
        /**
         * 统计每个字符串出现频率，哈希表
         * 用优先队列存放 （元素：出现频率）结构的数组
         */
        //记录元素出现频率的哈希表按照键值字典序排列
        Map<String, Integer> strFreq = new HashMap<>();

        PriorityQueue<Freq> freqs = new PriorityQueue<>(new Comparator<Freq>() {
            @Override
            public int compare(Freq o1, Freq o2) {
                if (o1.freq !=o2.freq)
                    return Integer.compare(o1.freq, o2.freq); //按照出现频率排序
                else
                    return compare(o1.s, o2.s);
            }
            private int compare(String o1, String o2) {
                if (o1.length()==0 && o2.length() == 0){
                    return 0;
                }
                if (o1.length()>0 && o2.length() == 0){
                    return 1;
                }
                if (o1.length()==0 && o2.length()>0){
                    return -1;
                }
                else {
                    if (o1.charAt(0) == o2.charAt(0)){
                        return compare(o1.substring(1), o2.substring(1));
                    }
                    if (o1.charAt(0) > o2.charAt(0)){
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        });

        for (int i = 0; i<strings.length; i++){
            if (strFreq.containsKey(strings[i])){
                strFreq.put(strings[i], strFreq.get(strings[i])+1);
            } else {
                strFreq.put(strings[i],1);
            }
        }

        for (String s: strFreq.keySet()){
            Freq f = new Freq();
            f.s = s;
            f.freq = strFreq.get(s);
            freqs.offer(f);
        }

        Freq[] res = new Freq[k];
        for (int i = 0; i<k; i++){
            res[i] = freqs.poll();
        }

        return res;
    }
}

class Freq {
    String s;
    int freq;
}