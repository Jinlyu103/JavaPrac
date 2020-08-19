package isPalindrome;

public class Solution {
    /**
     * 判断一个整数是否为回文数，即正读、反读都一样
     * 考虑回文数的特征：
     *      最高位和最低位的数相等，次高位和次低位的数相等，
     *      依次类推至回文中心
     *    这样就将整数num按照回文中心切分为两半，前后两半如果一样，说明是回文数
     *    否则不是回文数
     * @param num
     * @return
     */
    public boolean isPalindrome(int num){
        if (num<0 || (num%10==0 && num!=0)){ //负数不是回文数
            return false;
        }

        boolean ans = true;
        int rev = num%10; //rev记录num反转之后的数字
        while (num > rev){
            if (num > 10){
                num = num/10;
            }
            if (num > rev){
                rev = rev*10 + num%10;
            }
        }
        if (num != rev){
            ans = false;
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 121;
        Solution sol = new Solution();
        System.out.println(sol.isPalindrome(n));
    }
}
