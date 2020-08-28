package judgeCircle;

public class Solution {
    /**
     * 657. 判断机器人能否返回原点
     */
    public boolean judgeCircle(String moves){
        int x = 0, y = 0;
        for (int i = 0; i<moves.length(); i++){
            if (moves.charAt(i) == 'R'){
                x += 1; // move to right
            } else if (moves.charAt(i) == 'L'){
                x -=1; //move to left
            } else if (moves.charAt(i) == 'U'){
                y += 1; //move up
            } else if (moves.charAt(i) == 'D'){
                y -= 1; // move down
            } else {
                return false; //wrong direction
            }
        }
        if (x == 0 && y == 0)
            return true;
        return false;
    }
}
