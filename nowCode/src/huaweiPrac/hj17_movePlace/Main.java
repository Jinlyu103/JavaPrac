package huaweiPrac.hj17_movePlace;
import java.util.Scanner;

public class Main {
    /**
     * 坐标移动
     * A：向左
     * D：向右
     * W：向上
     * S：向下
     * 从（0.0）点开始移动，从输入字符串里读取坐标，并将最终输入结果输出到文件中
     * Q：非法坐标点需要丢弃是整个字符串丢弃，还是只丢弃一个点？
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String s = sc.nextLine();
            String[] strings = s.split(";"); //以分号分隔
            int[] pos = {0,0}; //初始坐标位置为（0,0）
            for (int i = 0; i<strings.length; i++){
                String act = strings[i];
                //判断act的合法性
                if (isValid(act)){
                    if (act.charAt(0) == 'A'){ //向左
                        pos[0] = pos[0] - Integer.parseInt(act.substring(1));
                    } else if (act.charAt(0) == 'D'){
                        pos[0] = pos[0] + Integer.parseInt(act.substring(1));
                    } else if (act.charAt(0) == 'W'){ //向上
                        pos[1] = pos[1] + Integer.parseInt(act.substring(1));
                    } else if (act.charAt(0) == 'S'){
                        pos[1] = pos[1] - Integer.parseInt(act.substring(1));
                    }
                }
            }
            System.out.println("(" + pos[0] +"," + pos[1] + ")");
        }
    }

    public static boolean isValid(String act){
        if (act.equals(" ") || act.length() == 0 || act.length() > 3){
            return false;
        }
        char first = act.charAt(0);
        if (first == 'A' || first == 'D' || first=='W' || first == 'S'){
            for (int i = 1; i<act.length(); i++){
                if (!Character.isDigit(act.charAt(i))){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
