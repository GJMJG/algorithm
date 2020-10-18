package bit.edu.interview.hauwei.interviewCoding;

import java.util.Stack;

/**
 * 华为一面算法题：实现汉诺塔
 * <p>主要思想采用三个栈，并递归地实现，每次调用递归函数处理一个数据。
 */
public class Main {
    static Stack<Integer> input = new Stack<>();
    static Stack<Integer> res = new Stack<>();
    static Stack<Integer> temp = new Stack<>();

    public static void main(String[] args) {
        input.push(5);
        input.push(6);
        input.push(4);
        input.push(1);
        input.push(10);
        input.push(-5);
        input.push(50);
        getRes();
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }

    // 递归，每次只排序一个元素
    static public void getRes() {
        int count = 0;
        if (input.size() == 0) return; // 递归终止
        while (input.size() != 0) {
            int num = input.pop();
            if (res.size() == 0 || count == 0) {
                res.push(num);
                count++; // 表示已经加入
            } else if (res.size() != 0 && num > res.peek()) {
                temp.push(res.pop());
                res.push(num);
            } else {
                temp.push(num);
            }
        }
        Stack<Integer> a = input;
        input = temp;
        temp = a;
        getRes(); // 下一次排序
    }
}
