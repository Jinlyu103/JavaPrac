package huaweiPrac.hj31_wordsReversion;

import org.omg.PortableServer.POAPackage.ObjectAlreadyActive;

import java.time.chrono.IsoEra;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    /**
     *  对字符串中的所有单词进行倒排
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String str= sc.nextLine();
            String[] words = str.split(" ");

            List<String> res = new ArrayList<>();
            for (int i = words.length-1; i>=0; i--){
                StringBuffer strBuf = new StringBuffer();
                for (int j = 0; j<words[i].length(); j++){
                    if (Character.isLetter(words[i].charAt(j))){
                        strBuf.append(words[i].charAt(j));
                    } else {
                        strBuf.append(" ");
                    }
                }
                String[] toAdd = strBuf.toString().split(" ");
                for (int k = toAdd.length-1; k>=0; k--){
                    res.add(toAdd[k]);
                }
            }
            StringBuffer ans = new StringBuffer();
            for (int i = 0; i<res.size(); i++){
                if (i !=res.size()-1){
                    ans.append(res.get(i) + " ");
                } else {
                    ans.append(res.get(i));
                }
            }
            if (ans.charAt(ans.length()-1) == ' '){ //删除末尾空格
                ans.deleteCharAt(ans.length()-1);
            }
            System.out.println(ans.toString());
        }
        sc.close();
    }
}
