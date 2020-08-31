package canVisitAllRooms;

import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class Solution {
    /**
     * 对于每个房间i都有一串钥匙列表rooms[i]，每个钥匙rooms[i][j] = v表示可以打开编号为v的房间
     * 思路：是否能拿到所有房间的钥匙,深度优先搜索
     * @param rooms
     * @return
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms){
        int n = rooms.size(); //房间数

        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        HashSet<Integer> visited = new HashSet<>();//已经访问过的房间
        //visited.add(0); //0号房间已经访问

        while (!stack.isEmpty()){
            int cur = stack.pop(); //当前所在房间
            visited.add(cur); //已经访问
            List<Integer> keys = rooms.get(cur); //获取当前所在房间的钥匙
            for (int i = 0; i<keys.size(); i++){
                if (!visited.contains(keys.get(i))) //如果钥匙i对应房间没有被访问
                    stack.push(keys.get(i));
            }
        }

        if (visited.size() == n){
            return true;
        }
        return false;
    }
}
