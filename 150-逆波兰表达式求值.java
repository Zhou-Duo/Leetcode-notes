import java.util.Stack;

/* 
 * 时间复杂度：O(n)，其中 nn 是数组 tokens 的长度。需要遍历数组 tokens 一次，计算逆波兰表达式的值。
 * 空间复杂度：O(n)，其中 n 是数组 tokens 的长度。使用栈存储计算过程中的数，栈内元素个数不会超过逆波兰表达式的长度
 */
class Solution {
    final String ADD = "+";
    final String MINUS = "-";
    final String MULTIPLY = "*";
    final String DIVIDE = "/";

    public int evalRPN(String[] tokens) {

        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (isNumber(token))
                stack.push(Integer.parseInt(token));
            else {
                int num2 = stack.pop();
                int num1 = stack.pop();
                switch (token) {
                    case ADD:
                        stack.push(num1 + num2);
                        break;
                    case MINUS:
                        stack.push(num1 - num2);
                        break;
                    case MULTIPLY:
                        stack.push(num1 * num2);
                        break;
                    case DIVIDE:
                        stack.push(num1 / num2);
                        break;
                    default:
                        break;
                }
            }
        }
        return stack.pop();
    }

    public boolean isNumber(String token) {
        // 注意 String 类中，“==“比较的是两个字符串地址，若比较内容应用 equals()
        return !(token.equals(ADD) || token.equals(MINUS) || token.equals(MULTIPLY) || token.equals(DIVIDE));
    }
}