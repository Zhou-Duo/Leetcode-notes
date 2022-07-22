import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

class Solution {
    public String decodeString(String s) {
        Deque<Character> charStack = new LinkedList<>();
        Deque<Integer> numStack = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c))
                numStack.push(Character.getNumericValue(c));
            else if (c == ']') {
                List<Character> popOut = new LinkedList<>();
                char rep = charStack.pop();
                while (rep != '[') {
                    popOut.add(rep);
                    rep = charStack.pop();
                }
                int k = numStack.pop();
                Collections.reverse(popOut);
                while (k != 0) {
                    for (char pop : popOut) {
                        charStack.push(pop);
                    }
                    k -= 1;
                }
            } else
                charStack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        while (!charStack.isEmpty()) {
            sb.append(charStack.pop());
        }
        return sb.toString();
    }
}