package xiecheng2021.t2;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.exit;

class WorkflowNode {
    String nodeId;
    int timeoutMillis;
    List<WorkflowNode> nextNodes;
    boolean initialised;

    public static WorkflowNode load(String value) {
        // Create head node;
        Map<String, WorkflowNode> map = new HashMap<>();
        WorkflowNode head = new WorkflowNode("HEAD", 0, null);
        map.put(head.nodeId, head);

        for (String nodeValue : value.split("\\|")) {
            String[] properties = nodeValue.split("\\`");
            WorkflowNode node = map.get(properties[0]);

            node.timeoutMillis = Integer.parseInt(properties[1]);
            node.initialised = true;

            // Check next nodes
            if (properties[2].equals("END")) {
                continue;
            }
            node.nextNodes = Arrays.stream(properties[2].split(","))
                    .map(p -> new WorkflowNode(p, 0, null))
                    .collect(Collectors.toList());
            node.nextNodes.forEach(p -> map.put(p.nodeId, p));

            map.put(node.nodeId, node);
        }

        return head;
    }

    public WorkflowNode(String nodeId, int timeoutMillis, List<WorkflowNode> nextNodes) {
        this.nodeId = nodeId;
        this.timeoutMillis = timeoutMillis;
        this.nextNodes = nextNodes;
    }
}

public class Main {
    public static void main(String args[]) {

        Scanner cin = new Scanner(System.in);
        while (cin.hasNext()) {
            WorkflowNode node = WorkflowNode.load(cin.next());
            if (node == null) {
                System.out.println(-1);
            } else {
                //DFS
                int maxTime = 0;
                int t = 0; //记录本次查询最长执行时间
                Stack<WorkflowNode> stack = new Stack<>();

                stack.push(node);
                WorkflowNode p = node;
                WorkflowNode cur = node;
                //栈不为空或者cur不为null的时候执行循环
                while (!stack.isEmpty() || cur!=null) {
                     cur = stack.pop();
                    //当前节点时间输入不合法，直接退出
                    if (cur.timeoutMillis < 0) {
                        System.out.println(-1);
                        exit(0);
                    }
                    if (cur != null){ //当前节点不为空
                        List<WorkflowNode> sons = cur.nextNodes;
                        t += cur.timeoutMillis; //加上当前节点的累计值
                        if (sons == null) { //当前节点为最终节点，没有孩子
                            maxTime = Math.max(maxTime, t);
                            t = t - cur.timeoutMillis; //减去当前节点的值
                            cur = null;
                        } else { //否则将孩子节点加入栈中
                            for (WorkflowNode w : sons) {
                                stack.push(w);
                            }
                        }
                    } else { //当前节点为空
                        t = p.timeoutMillis;
                        p = stack.pop() ;
                        cur = p;
                    }
                }
                System.out.println(maxTime);
            }
        }
    }
}