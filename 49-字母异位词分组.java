import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

// 1. 哈希映射，将字母异位词映射在一个桶内
/* 
 * 由于互为字母异位词的两个字符串包含的字母相同，
 * 因此对两个字符串分别进行排序之后得到的字符串一定是相同的，
 * 故可以将排序之后的字符串作为哈希表的键。
 */

/* 
 * 时间复杂度：O(nklogk)，其中 n 是 strs 中的字符串的数量，k 是 strs 中的字符串的的最大长度。
 * 需要遍历 n 个字符串，对于每个字符串，需要 O(klogk) 的时间进行排序以及 O(1) 的时间更新哈希表，因此总时间复杂度是 O(nklogk)。
 * 空间复杂度：O(nk)，需要用哈希表存储全部字符串。
  */
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String string : strs) {
            char[] charArrays = string.toCharArray();
            Arrays.sort(charArrays);
            // 注意此处不能用 charArrays.toString() 会返回 charArrays 的编码
            // Arrays.toString(): Returns a string representation of the contents of the specified array.
            String key = Arrays.toString(charArrays);
            if (!map.containsKey(key)) 
                map.put(key, new ArrayList<String>());
            map.get(key).add(string);
            
        }
        return new ArrayList<>(map.values());
    }
}