import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        for (char c : s.toCharArray()) {
            if (stack.isEmpty() || pairs.get(c) != stack.peek())
                stack.add(c);
            else stack.pop();
        }
        return stack.isEmpty();
    }
}