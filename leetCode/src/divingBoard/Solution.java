package divingBoard;

public class Solution {
    public int[] divingBoard(int shorter, int longer, int k) {
        /**
         * 正好用k块木板建造跳水板，长度有两种shorter和longer
         * 输出生成跳水板所有可能的长度
         * 枚举
         */
        if (k == 0){
            return new int[k];
        }

        if (shorter == longer){
            return new int[]{shorter*k};
        }
        int[] res = new int[k+1];
        for (int i = 0; i <=k ; i++){
            //用i块longer和k-i块shorter建造
            int board = longer * i +shorter*(k-i);
            res[i] = board;
        }
        return res;
    }
}
