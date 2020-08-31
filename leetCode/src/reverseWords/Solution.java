package reverseWords;

public class Solution {
    public String reverseWords(String s){
        if (s.length()<=1){
            return s;
        }
        String[] strArr = s.split(" ");
        StringBuffer strB = new StringBuffer();
        for(int i = 0; i<strArr.length; i++){
            StringBuffer tmp = new StringBuffer(strArr[i]);
            tmp.reverse();
            if (i != strArr.length-1){
                strB.append(tmp+" ");
            } else {
                strB.append(tmp);
            }
        }
        return strB.toString();
    }
}
