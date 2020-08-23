package rangeBitwiseAnd;

public class Solution {
    /**
     * 201. 数字范围按位与
     * 给定范围[m,n]，其中0<=m<=n<=2147483647
     * 返回此范围内所有数字的按位与，包含m，n两端点
     *
     * 思路：1&1 = 1， 任意数&0均为0
     * 转换为二进制，求公共前缀（右移x位，相等），然后将公共前缀左移x位
     * 即为所求结果
     */
    public int rangeBitwiseAnd(int m, int n){
        int x = 0;
        while (m < n){
            m = m>>1;
            n = n>>1;
            x ++;
        }
        return m<<x;
    }
}
