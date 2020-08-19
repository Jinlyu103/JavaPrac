package xiecheng2021.t2;

import java.util.*;

public class Main1 {
    static int maxTime = 0;
    public static void main(String args[]) {

        Scanner cin = new Scanner(System.in);
        while (cin.hasNext()) {
            WorkflowNode node = WorkflowNode.load(cin.next());
            if (node == null){
                System.out.println(-1);
            }else {
                //DFS
                dfs(node, 0);
                System.out.println(maxTime);
            }
        }
    }

    public static void dfs(WorkflowNode node, int t){
        if (node.nextNodes == null){
            //t += node.timeoutMillis;
            maxTime = Math.max(maxTime, t);
            return;
        }
        List<WorkflowNode> sons = node.nextNodes;
        for (WorkflowNode w: sons){
            dfs(w, t+w.timeoutMillis);
        }
        return ;
    }
}