import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public String decodeMessage(String key, String message) {
        Map<Character, Character> map = new HashMap<Character, Character>();
        for (char c : key.toCharArray()) {
            if (c != ' ' && !map.containsKey(c))
                map.put(c, (char) ('a' + map.size()));
        }
        StringBuilder sb = new StringBuilder();
        for (char c : message.toCharArray())
            sb.append(map.getOrDefault(c, ' '));
        return sb.toString();
    }
}