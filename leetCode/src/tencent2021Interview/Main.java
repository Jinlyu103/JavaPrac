package tencent2021Interview;

import java.util.Arrays;

class Node{
    int id;
    String name;

    Node(){}
    Node(int i, String  n){
        this.id = i;
        this.name = n;
    }
}

public class Main {
    public static void main(String[] args) {
        Node[] arr = new Node[5];
        for (int i = 0; i<5; i++){
            int id = (int) (Math.random() * 5);
            arr[i] = new Node(id, "b" + id);
            System.out.println("id: " +arr[i].id + " name:" + arr[i].name);
        }
        System.out.println();
        //匿名函数比较
        Arrays.sort(arr,(a1,a2) -> Integer.compare(a1.id, a2.id));

        for (int i = 0; i<5; i++){
            System.out.println("id: " +arr[i].id + " name:" + arr[i].name);
        }
    }
}
