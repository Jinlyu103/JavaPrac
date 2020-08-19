package divisorGame;

public class Solution {
    /**
     * 除数游戏，A和B轮流玩游戏，A先开局
     * 起初，黑板上有数字N。在每个玩家的回合，玩家执行以下操作：
     * 选出任一x,0<x<N,且N%x=0
     * 用N-x替换黑板上的数字
     * 动态规划：dp[i]表示Alice当前数字为i的时候能否获胜
     * dp[i] =
     * @param N
     * @return
     */
    public boolean divisorGame(int N){
        return N%2==0;
    }
}
