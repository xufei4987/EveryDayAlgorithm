package datastructure.list;

public class _有效的括号 {
    public static void main(String[] args) {
        String str = "({}[)]";
        if (isValid(str)) {
            System.out.println("括号是合法的");
        } else {
            System.out.println("括号是不合法的");
        }
    }

    public static boolean isValid(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (isLeftBracket(str.charAt(i))) {
                stack.push(str.charAt(i));
            } else {
                if (stack.isEmpty() && isRightBracket(str.charAt(i))) {
                    return false;
                } else if (!stack.isEmpty() && isRightBracket(str.charAt(i))) {
                    if (!isMatch(stack.pop(),str.charAt(i))) {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }

    private static boolean isLeftBracket(Character c) {
        if (c == '(' || c == '[' || c == '{') {
            return true;
        }
        return false;
    }

    private static boolean isRightBracket(Character c) {
        if (c == ')' || c == ']' || c == '}') {
            return true;
        }
        return false;
    }

    private static boolean isMatch(Character left, Character right) {
        if ((left == '(' && right == ')')
                || (left == '{' && right == '}')
                || (left == '[' && right == ']')) {
            return true;
        }
        return false;
    }
}
