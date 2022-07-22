import java.util.ArrayList;
import java.util.HashMap;

// HashMap
class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for (String string : strings) {
            String key = hashString(string);
            if (!map.containsKey(key))
                map.put(key, new ArrayList<>());
            map.get(key).add(string);
        }
        return new ArrayList<>(map.values());
    }

    // 将 string 中相邻字母的 shift 作为 key
    private String hashString(String string) {
        StringBuffer sb = new StringBuffer();
        sb.append(0);
        for (int i = 1; i < string.length(); i++) {
            sb.append((string.charAt(i) - string.charAt(i - 1) + 26) % 26);
            // 注意 shift 中间要穿插间隔符，否则可能出现 'abc'和 'al'的 key相同 (也可考虑将 string.length() hash 到第一位)
            sb.append("#");
        }
        return sb.toString();
    }
}