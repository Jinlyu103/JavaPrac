package nowcode.quehun;

import java.util.*;

public class Main {
    /**
     * 麻将游戏：总共36张牌每张牌数字1-9，每个数字4张牌
     * 手中有13张牌，请问再抽取一张什么样的牌可以胡牌，若不可，返回0
     */
    public List<Integer> findCards(List<Integer> inHands){
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 1; i < 10 ; i++) {
            inHands.add(i);
            if (canWin(inHands)){ //如果加入牌i可以胡牌的话，将i加入res中
                res.add(i);
            }
            inHands.remove(inHands.indexOf(i));
        }
        return res;
    }

    private boolean canWin(List<Integer> hands){
        if (hands.isEmpty()){
            return true;
        }
        //boolean flag = false;
        Collections.sort(hands);
        int n = hands.size();
        int firstCard = hands.get(0);
        int count0 = 1;
        //牌面必须合理，即每张牌最多出现4次
        int valid = 0, last = hands.get(0);
        for (int i = 1; i<hands.size(); i++) {
            if (hands.get(i) == last){
                valid ++;
            } else {
                valid = 1;
                last = hands.get(i);
            }
            if (valid > 4){
                return false;
            }
            if (hands.get(i) == firstCard){//统计第一个数字出现的次数
                count0 ++;
            }
        }

        //如果没出现过雀头，且第一个数字出现的次数大于等于2，看去掉雀头剩下的牌能否胡牌
        if (n%3!=0 && count0 >= 2 && canWin(hands.subList(2, hands.size()))){
            return true;
        }
        //如果第一个数字出现次数大于等于3，去掉这个刻子看剩下能否胡牌
        if (count0 >= 3 && canWin(hands.subList(3, hands.size()))){
            return true;
        }
        //如果存在顺子，移除顺子后剩下的能胡牌
        if (hands.contains(firstCard+1) && hands.contains(firstCard+2)){
            List<Integer> copy = new ArrayList<Integer>();
            for (Integer x:hands) {
                copy.add(x);
            }
            copy.remove(0);
            copy.remove(copy.indexOf(firstCard+1));
            copy.remove(copy.indexOf(firstCard+2));
            if (canWin(copy)){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> inHands = new ArrayList<Integer>();
        for (int i = 0; i<13;i++){
            if (sc.hasNext()){
                int card = sc.nextInt();
                inHands.add(card);
            }
        }
        Main sol = new Main();
        List<Integer> ans = sol.findCards(inHands);
        if (ans.isEmpty()){
            System.out.println(0);
        } else {
            for (Integer a:ans) {
                System.out.print(a + " ");
            }
        }
    }
}
