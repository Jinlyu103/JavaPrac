package CQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

class CQueue {
    public Stack<Integer> stk1;
    public Stack<Integer> stk2;

    public CQueue() {
        stk1 = new Stack<>();
    }

    public void appendTail(int value) {
        //入栈：操作stk1，同步stk2
        stk1.push(value);
    }

    public int deleteHead() {
        //出栈，操作stk2，同步stk1
        if (stk1.size() == 0)
            return -1;

        stk2 = new Stack<>();
        int res;
        while (!stk1.isEmpty())
            stk2.push(stk1.pop());
        res = stk2.pop();
        while (!stk2.isEmpty())
            stk1.push(stk2.pop());

        return res;
    }

    public static void main(String[] args) {

    }
}

