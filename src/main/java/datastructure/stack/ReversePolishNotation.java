package datastructure.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰表达式 （后缀表达式）
 * 1 + ( ( 2 + 3 ) * 4 ) -5 => 1 2 3 + 4 * + 5 -
 * 转化步骤：
 * 1. 初始化2个栈    运算符栈s1  中间结果栈s2
 * 2. 扫描中缀表达式
 * 3. 遇到操作数push入s2
 * 4. 遇到运算符时
 * 4.1 s1为空 || 为"(" 直接push入s1
 * 4.2 如果优先级高于栈顶符号，push入s1
 * 4.3 否则，s1栈顶符号pop并push入s2，再将运算符进行4.1的逻辑判断
 * 5. 遇到括号时
 * 5.1 为"("入s1栈
 * 5.2 为")"则依次弹出s1中的运算符并压入到s2中，直到弹出"("为止，此时就消除了一对括号
 * 6. 重复2 - 5步骤
 * 7. 将s1剩余的运算符弹出并压入到s2
 * 8. 将s2中的元素依次弹出并逆序排列后得到后缀表达式
 * 计算步骤：
 * 1. 创建一个栈s
 * 2. 遍历逆波兰表达式
 * 1.1 遇到数字压入s中
 * 1.2 遇到符号则从s中弹出2个数进行计算，再压回栈中
 * 3. 最后留在s中的数字为计算结果
 */
public class ReversePolishNotation {
    public static void main(String[] args) {
        ReversePolishNotation reversePolishNotation = new ReversePolishNotation();
        List expressList = reversePolishNotation.infixExpressToList("10-5+(2+3)*3");
        System.out.println(expressList);
        List notationList = reversePolishNotation.transformToNotation(expressList);
        System.out.println(notationList);
        int result = reversePolishNotation.calculate(notationList);
        System.out.println(result);
    }

    /**
     * 中缀表达式转换为后缀表达式
     *
     * @param expression
     * @return
     */
    public List<String> transformToNotation(List<String> expression) {
        Stack<String> s1 = new Stack<>();
        //可以用list替代s2  因为s2没有出栈的操作
//        Stack<String> s2 = new Stack<>();
        List<String> s2 = new ArrayList<>();
        for (String s : expression) {
            if (s.matches("\\d+")) {
                s2.add(s);
            } else if (s1.empty() || s.equals("(")) {
                s1.push(s);
            } else if (s.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                //弹出括号
                s1.pop();
            } else {
                while (!s1.empty() && !s1.peek().equals("(") && getPriority(s1.peek()) >= getPriority(s)) {
                    s2.add(s1.pop());
                }
                s1.add(s);
            }
        }
        //将剩余的符号加入到s2
        while (!s1.empty()) {
            s2.add(s1.pop());
        }
        return s2;
    }

    /**
     * 获取当前符号的优先级
     *
     * @param operation
     * @return
     */
    private int getPriority(String operation) {
        if ("+".equals(operation) || "-".equals(operation)) {
            return 1;
        } else if ("*".equals(operation) || "/".equals(operation)) {
            return 2;
        } else {
            System.out.println(operation + "是不合法的符号");
            return 0;
        }
    }

    /**
     * "1 + 2 + 3" => List{1,+,2,+,3}
     *
     * @param expression
     * @return
     */
    public List<String> expressToList(String expression) {
        if (expression == null || expression.trim() == "") {
            return Collections.EMPTY_LIST;
        }
        List<String> list = new ArrayList<>();
        String[] ss = expression.split(" ");
        for (String s : ss) {
            list.add(s);
        }
        return list;
    }

    /**
     * "10+2+3" => List{10,+,2,+,3}
     *
     * @param infixExpression
     * @return
     */
    public List infixExpressToList(String infixExpression) {
        List<String> list = new ArrayList<>();
        int idx = 0;
        char c;
        String str;
        do {
            if ((c = infixExpression.charAt(idx)) < '0' || (c = infixExpression.charAt(idx)) > '9') {
                list.add(c + "");
                idx++;
            } else {
                str = "";
                while (idx < infixExpression.length() && (c = infixExpression.charAt(idx)) >= '0' && (c = infixExpression.charAt(idx)) <= '9') {
                    str += c;
                    idx++;
                }
                list.add(str);
            }
        } while (idx < infixExpression.length());
        return list;
    }

    public int calculate(List<String> notationList) {
        int num1;
        int num2;
        Stack<String> stack = new Stack<>();
        for (String s : notationList) {
            if (s.matches("\\d+")) {
                stack.push(s);
            } else {
                num2 = Integer.valueOf(stack.pop());
                num1 = Integer.valueOf(stack.pop());
                switch (s) {
                    case "+":
                        stack.push(String.valueOf(num1 + num2));
                        break;
                    case "-":
                        stack.push(String.valueOf(num1 - num2));
                        break;
                    case "*":
                        stack.push(String.valueOf(num1 * num2));
                        break;
                    case "/":
                        stack.push(String.valueOf(num1 / num2));
                        break;
                    default:
                        throw new RuntimeException("表达式符号错误");
                }
            }
        }
        return Integer.valueOf(stack.pop());
    }
}
