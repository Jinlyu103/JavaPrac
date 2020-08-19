package LRUCache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    //LRU缓存机制：将最近使用的条目放在靠近缓存顶部的位置，
    //当一个新条目被访问时，将其放到缓存顶部。
    //当缓存达到极限，从缓存底部开始删除较早之前访问的条目
    //哈希表+双向链表：o(1)访问，O(1)删除

    class DLinkedNode{ //定义双向链表节点类
        int key; //存储键
        int value;//存储值
        DLinkedNode pre; //指向前驱节点
        DLinkedNode next;//指向后继节点

        public DLinkedNode(){}
        public DLinkedNode(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity){
        this.capacity = capacity;
        this.size = 0;
        head = new DLinkedNode(); //使用伪头部和伪尾部节点
        tail = new DLinkedNode();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key){ //获取key所对应的值
        DLinkedNode node = cache.get(key); //通过哈希表定位到节点
        if (node == null){
            return -1;//key not exists, return -1
        }
        moveToHead(node); //将节点移动到链表头部
        return node.value;
    }

    public void put(int key, int value){
        DLinkedNode node = cache.get(key);
        if(node == null){ //如果key不存在，创建一个新的节点
            DLinkedNode newNode = new DLinkedNode(key,value);
            cache.put(key, newNode); //将其加入到哈希表中
            addToHead(newNode);      // 加入到链表头部
            size ++;
            if(size > capacity){
                DLinkedNode tail = removeTail(); //删除的尾部节点,并将删除的节点返回
                cache.remove(tail.key);
                size--;
            }
        }
        else { //key存在，更新key值
            node.value = value;
            moveToHead(node); //将节点移动至头部
        }
    }

    private void moveToHead(DLinkedNode node){
        removeNode(node);
        addToHead(node);
//        DlinkedNode p = node.pre;
//        DlinkedNode q = node.next;
//        p.next = q;
//        q.pre = p;
//        node.pre = head;
//        node.next = head.next;
//        head.next.pre = node;
//        head.next = node;
    }

    private void addToHead(DLinkedNode newNode){
            newNode.pre = head;
            newNode.next = head.next;
            head.next.pre = newNode;
            head.next = newNode;
    }

    private void removeNode(DLinkedNode node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
    private DLinkedNode removeTail(){
        DLinkedNode rmNode = tail.pre;
        removeNode(rmNode);
        return rmNode;
    }
}
