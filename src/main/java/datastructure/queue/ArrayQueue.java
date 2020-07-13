package datastructure.queue;

import java.util.Scanner;

/**
 * 队列头部取数据
 * 队列尾部加数据
 * 注意：这种写法数组空间只能用一次
 *
 * @param <T>
 */
public class ArrayQueue<T> {
    private int maxSize;
    private int front; //队列头指针 -->指向队头的前一个数据
    private int rear; //队列尾指针 -->指向队尾数据
    private T[] datas; //存放数据

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        front = -1;
        rear = -1;
        datas = (T[]) new Object[maxSize];
    }

    private boolean isFull() {
        return rear == maxSize - 1;
    }

    private boolean isEmpty() {
        return front == rear;
    }

    public void add(T data) {
        if (!isFull()) {
            datas[++rear] = data;
            return;
        }
        throw new RuntimeException("queue is full,cannot add data");
    }

    public T get() {
        if (!isEmpty()) {
            return datas[++front];
        }
        throw new RuntimeException("queue is empty,cannot get data");
    }

    //偷看一下队头数据
    public T peekHead() {
        if (!isEmpty()) {
            return datas[front + 1];
        }
        throw new RuntimeException("queue is empty,cannot get data");
    }

    //偷看一下队尾数据
    public T peekTail() {
        if (!isEmpty()) {
            return datas[rear];
        }
        throw new RuntimeException("queue is empty,cannot get data");
    }

    public void showQueue() {
        for (T data : datas) {
            System.out.printf("%s\t", data.toString());
        }
    }

    public static void main(String[] args) {
        ArrayQueue<String> arrayQueue = new ArrayQueue<>(3);
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("【提示】");
            System.out.println("输入'a'添加数据 ");
            System.out.println("输入'g'取出数据 ");
            System.out.println("输入's'查看数据 ");
            System.out.println("输入'h'查看队头数据 ");
            System.out.println("输入't'查看队尾数据 ");
            System.out.println("输入'e'退出 ");
            char c = scanner.nextLine().charAt(0);
            switch (c) {
                case 'a':
                    System.out.println("输入一个字符串：");
                    arrayQueue.add(scanner.nextLine());
                    break;
                case 'g':
                    String s = arrayQueue.get();
                    System.out.println("从队列中取出的字符串：" + s);
                    break;
                case 's':
                    System.out.print("队列数据为：");
                    arrayQueue.showQueue();
                    System.out.println();
                    break;
                case 'h':
                    System.out.println("队列头数据为：" + arrayQueue.peekHead());
                    break;
                case 't':
                    System.out.println("队列尾数据为：" + arrayQueue.peekTail());
                    break;
                case 'e':
                    loop = false;
                    break;
                default:
                    System.out.println("无此命令，请重新输入");
                    break;
            }
        }
    }
}
