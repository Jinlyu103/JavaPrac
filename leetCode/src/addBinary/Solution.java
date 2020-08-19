package addBinary;

public class Solution {
    public String addBinary(String a, String b){
        StringBuilder ans = new StringBuilder();
        int carry = 0;
        //从低位开始计算往高位加
        int aIdx = a.length()-1, bIdx = b.length()-1;
        while (aIdx >= 0 && bIdx >= 0){
            int aNum = a.charAt(aIdx) - '0';
            int bNum = b.charAt(bIdx) - '0';
            int tmp = aNum + bNum + carry;
            if (tmp < 2){
                //放在结果的前端
                ans.append(Integer.toString(tmp), 0, 1);
            } else {
                ans.append(Integer.toString(tmp%2),0,1);
            }
            carry = tmp/2;
            aIdx --;
            bIdx --;
        }
        while (aIdx >= 0){
            int aNum = a.charAt(aIdx) - '0';
            int tmp = aNum + carry;
            if (tmp < 2){
                ans.append(Integer.toString(tmp));
            } else {
                ans.append(Integer.toString(tmp%2));
            }
            carry = tmp/2;
            aIdx --;
        }
        while (bIdx >= 0){
            int bNum = b.charAt(bIdx) - '0';
            int tmp = bNum + carry;
            if (tmp < 2){
                ans.append(Integer.toString(tmp));
            } else {
                ans.append(Integer.toString(tmp%2));
            }
            carry = tmp/2;
            bIdx --;
        }
        if (carry > 0){
            ans.append(Integer.toString(carry));
        }
        ans.reverse();
        return ans.toString();
    }

    public static void main(String[] args) {
        String a = "1010", b = "1011";
        Solution sol = new Solution();
        System.out.println(sol.addBinary(a,b));
    }
}
