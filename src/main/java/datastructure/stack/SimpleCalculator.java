package datastructure.stack;

/**
 * 利用中缀表达式实现一位数的加减乘除运算
 */
public class SimpleCalculator {
    public static void main(String[] args) {
        SimpleCalculator simpleCalculator = new SimpleCalculator();
        String expression = "3+5*3-9";
        int result = simpleCalculator.cal(expression);
        System.out.println("表达式 " + expression + " 的值为：" + result);
    }

    public int cal(String expression) {
        //创建2个栈分别用于存储数字和符号
        ArrayStack<Integer> numStack = new ArrayStack<>(10);
        ArrayStack<Integer> optStack = new ArrayStack<>(10);
        int idx = 0;
        while (true) {
            char c = expression.charAt(idx);
            if (isOperator(c)) {
                //如果符号栈为空，则压入栈
                if (optStack.isEmpty()) {
                    optStack.push(Integer.valueOf(c));
                } else { //不为空有2种情况
                    //当前符号的优先级<栈顶符号的优先级,则弹出数字进行计算再入栈
                    if (getPriority(c) < getPriority(optStack.peek())) {
                        int num1 = numStack.pop();
                        int num2 = numStack.pop();
                        int opt = optStack.pop();
                        numStack.push(calculate(num1, num2, opt));
                        optStack.push(Integer.valueOf(c));
                    } else {
                        optStack.push(Integer.valueOf(c));
                    }
                }
            } else {//是数字则直接入栈
                numStack.push(c - 48); //字符‘1’的asc码为49
            }
            idx ++;
            if(idx == expression.length()){
                break;
            }
        }
        //依次出栈进行运算
        while (true){
            if(optStack.isEmpty()){
                break;
            }
            int num1 = numStack.pop();
            int num2 = numStack.pop();
            int opt = optStack.pop();
            numStack.push(calculate(num1, num2, opt));
        }
        return numStack.pop();
    }

    public int getPriority(int c) {
        if (c == '+' || c == '-') {
            return 0;
        } else if (c == '*' || c == '/') {
            return 1;
        } else {
            return -1;
        }
    }

    public int calculate(int num1, int num2, int operator) {
        int result = 0;
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                break;
        }
        return result;
    }

    public boolean isOperator(int val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }
}
