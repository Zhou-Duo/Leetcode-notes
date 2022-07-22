import java.util.HashMap;
import java.util.HashSet;

class ValidWordAbbr {
    Map<String, Set<String>> map = new HashMap<>();

    public ValidWordAbbr(String[] dictionary) {
        for (String string : dictionary) {
            String abbr = getAbbr(string);
            if (!map.containsKey(abbr)) {
                map.put(abbr, new HashSet<>());
            }
            map.get(abbr).add(String);
        }
    }

    public boolean isUnique(String word) {
        String abbr = getAbbr(word);
        if (map.containsKey(abbr)) {
            if (!map.get(abbr).contains(word) || map.get(abbr).size() > 1)
                return false;
        }
        return true;
    }

    private String getAbbr(String string) {
        int n = string.length();
        if (n < 3)
            return string;
        StringBuffer sb = new StringBuffer();
        sb.append(string.charAt(0));
        sb.append(n - 2);
        sb.append(string.charAt(n - 1));
        return sb.toString();
    }
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param_1 = obj.isUnique(word);
 */