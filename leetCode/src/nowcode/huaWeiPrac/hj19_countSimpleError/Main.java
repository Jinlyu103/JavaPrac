package nowcode.huaWeiPrac.hj19_countSimpleError;

import java.util.*;

public class Main {
    /**
     * 简单错误记录
     * 能记录出错的代码所在的文件名称和行号
     * 处理：
     *      1、记录最多8条错误记录，循环记录（即最后只输出最后出现的8条错误记录），
     *          对相同的错误记录(净文件名（保留最后16位)和行号完全匹配)只记录一条，错误计数增加
     *      2、超过16个字符的问价名称，只记录文件的最后有效16个字符
     *      3、输入的文件可能带路径，记录文件名称不能带路径
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Integer> errNums= new HashMap<String, Integer>(); //错误:name-行号，次数
        Queue<String> q = new LinkedList<>();

        while (sc.hasNext()){
            String[] info = new String[2]; //文件名，行号
            info[0] = sc.next(); //输入文件名
            info[1] = sc.next(); //输入行号

            //处理文件名
            String[] fName = info[0].split("\\\\");
            String fn = fName[fName.length-1];
            if (fn.length() > 16){ //截取文件名
                fn = fn.substring(fn.length()-16);
            }

            //拼接文件名和行号
            String fInfo = fn+ " " +info[1];
            if (errNums.containsKey(fInfo)){
                errNums.put(fInfo, errNums.get(fInfo)+1);
            } else {
                errNums.put(fInfo, 1);
                if (q.size() >= 8){
                    q.poll();
                }
                q.offer(fInfo);
            }
        }

        while (!q.isEmpty()){
            String f = q.poll();
            System.out.println(f + " " + errNums.get(f));
        }
        sc.close();
    }
}
