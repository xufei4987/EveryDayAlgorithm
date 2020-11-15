package datastructure.stack;

import java.util.Scanner;

public class ArrayStack<E> {
    private int maxSize;
    private Object[] datas;
    private int top = -1;

    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        datas = new Object[maxSize];
    }

    public boolean isFull(){
        return maxSize - 1 == top;
    }

    public boolean isEmpty(){
        return -1 == top;
    }

    public void push(E e){
        if (isFull()) {
            System.out.println("stack is full, cannot push");
            return;
        }
        datas[++top] = e;
    }

    public E pop(){
        if (isEmpty()) {
            System.out.println("stack is empty, cannot pop");
            return null;
        }
        return (E) datas[top--];
    }

    public void listStack(){
        if (isEmpty()) {
            System.out.println("stack is empty");
            return;
        }
        for (int i = top; i >= 0; i--){
            System.out.println(datas[i].toString());
        }
    }

    public E peek(){
        return (E) datas[top];
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>(4);
        String key = null;
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop){
            System.out.println("show:显示栈");
            System.out.println("exit:退出");
            System.out.println("push:入栈");
            System.out.println("pop:出栈");
            String line = scanner.next();
            switch (line){
                case "show":
                    stack.listStack();
                    break;
                case "push":
                    System.out.println("请输入一个数字：");
                    int number = scanner.nextInt();
                    stack.push(number);
                    break;
                case "pop":
                    System.out.println("出栈的数字为：" + stack.pop());
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    System.out.println("请输入正确的指令");;
                    break;
            }
        }
    }
}
