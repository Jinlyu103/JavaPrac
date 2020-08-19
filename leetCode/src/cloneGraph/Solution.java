package cloneGraph;

import java.util.*;

// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class Solution {
    public Node cloneGraph(Node node){
        /**
         * 先通过BFS，将所有节点的值复制一份，并将其置于哈希表中，key为Node的值，值为邻居列表
         * 通过遍历哈希表，建立新的节点之间的邻居关系
         */
        if (node == null){
            return null;
        }

        Map<Integer, List<Node>> nodes = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        while (!q.isEmpty()){
            Node cur = q.poll();
            nodes.put(cur.val, cur.neighbors);
            for (Node n:cur.neighbors){
                if (nodes.containsKey(n.val)){
                    continue;
                }
                nodes.put(n.val, n.neighbors);
                q.offer(n); //未访问的邻居节点加入队列中
            }
        }

        //建立没有邻居的节点
        Map<Integer, Node> newNodes = new HashMap<>();
        for (Integer i: nodes.keySet()){
            Node n = new Node(i);
            newNodes.put(i, n);
        }

        //为newNodes中的节点n建立相邻关系
        //节点n的邻居可以从哈希表nodes中通过节点的值获得
        //并且邻居节点都已经创建了，可以直接从newNodes中通过值获得节点
        for (Integer i: newNodes.keySet()){
            Node n = newNodes.get(i);
            List<Node> nbs = new ArrayList<>();
            //添加节点n的邻居
            for (Node n_node: nodes.get(i)){
                nbs.add(newNodes.get(n_node.val));
            }
            n.neighbors = nbs;
        }
        return newNodes.get(node.val);
    }
}
