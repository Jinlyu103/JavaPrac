package LFUCache;

import java.util.HashMap;
import java.util.Map;


public class LFUCache {
    //使用哈希表+双向链表来实现
    //LFUCache:最不经常使用算法，用计数器来记录条目访问的频率，
    // 当缓存达到最大容量时，在插入新的条目之前，删除最不经常访问的条目，
    // 当两个key相同的访问频率，删除最久未使用的条目
    // 链表顶部的访问次数最高
    class DLinkedNode{
        int key;
        int value;
        int freq; //记录节点的访问频次
        DLinkedNode pre;
        DLinkedNode next;

        public DLinkedNode(){}
        public DLinkedNode(int k, int v){
            key = k;
            value = v;
            freq = 1;
        }
    }
    private int size;
    private int capacity;
    private Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
    DLinkedNode head, tail; //双向链表的头指针和尾指针

    public LFUCache(int capacity){
        this.capacity = capacity;
        this.size = 0;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.pre = head;
    }

    public int getKey(int k){
        DLinkedNode node = cache.get(k);
        if (node == null){
            return -1;
        }
        //节点node的访问频次+1
        node.freq ++;

        //先将节点node从链表中删除成为独立的节点
        node.pre.next = node.next;
        node.next.pre = node.pre;

        moveNode(node); //向前移动节点到合适的位置
        return node.value;
    }

    public void put(int k, int v){
        if (capacity == 0){
            return;
        }
        DLinkedNode node = cache.get(k);
        if (node == null){ // 节点不存在，新建一个节点
            DLinkedNode newNode = new DLinkedNode(k,v);
            if (size >= capacity){
                //删除链表尾部节点
                DLinkedNode rmNode = removeTail();
                cache.remove(rmNode.key);
            }
            addToLinkList(newNode); //将节点加入到链表合适的位置
            cache.put(k, newNode);
            size ++;
        }
        else{
            //节点存在，更新节点值，移动节点位置
            node.value = v;
            node.freq ++;
            node.pre.next = node.next;
            node.next.pre = node.pre;
            moveNode(node);
        }
    }

    private void moveNode(DLinkedNode node) {
        //向前移动节点至访问频次刚好大于等于其后继节点
        DLinkedNode p = node.pre;
        while (p!=head && p.freq <= node.freq){
            p = p.pre;
        }
        node.next = p.next;
        node.pre = p;
        p.next.pre = node;
        p.next = node;
//        if (p!=head && p.freq <= node.freq){
//            p.next = node.next;
//            node.next.pre = p;
//            p.pre.next = node;
//            p.pre = node;
//        }
//        else{ //不移动，放回原位置
//            p.next.pre = node;
//            p.next = node;
//        }
    }

    private void addToLinkList(DLinkedNode node){
        DLinkedNode tmp = tail.pre;
        while (tmp != head && tmp.freq <= node.freq){
            tmp = tmp.pre;
        }
        node.next = tmp.next;
        node.pre = tmp;
        tmp.next.pre = node;
        tmp.next = node;
    }

    private DLinkedNode removeTail(){
        //删除表尾节点
        DLinkedNode res = tail.pre;
        res.pre.next = tail;
        tail.pre = res.pre;
        return res;
    }

    public static void main(String[] args){
        LFUCache mycache = new LFUCache(2);
        mycache.put(1,1);
        mycache.put(2,2);
        System.out.println(mycache.getKey(1));
        mycache.put(3,3);
        System.out.println(mycache.getKey(2));
        System.out.println(mycache.getKey(3));
        mycache.put(4,4);
        System.out.println(mycache.getKey(1));
        System.out.println(mycache.getKey(3));
        System.out.println(mycache.getKey(4));
    }
}
