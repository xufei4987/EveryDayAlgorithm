package datastructure.list;

public class Stack<E>{



    private ArrayList<E> list = new ArrayList<>();

    public void push(E e){
        list.add(e);
    }

    public E pop(){
        return list.remove(list.size() - 1);
    }

    public E top(){
        return list.get(list.size() - 1);
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(11);
        stack.push(22);
        stack.push(33);
        stack.push(44);

        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
}
