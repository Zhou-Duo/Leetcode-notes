import java.util.HashSet;
import java.util.Set;

class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        Set<Character> jewelsSet = new HashSet<>();
        for (char jewel : jewels.toCharArray()) {
            jewelsSet.add(jewel);
        }
        int ans = 0;
        for (char stone : stones.toCharArray()) {
            if (jewelsSet.contains(stone))
                ans += 1;
        }
        return ans;
    }
}