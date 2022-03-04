import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

// 1. 哈希表
class Solution1 {
    public char firstUniqChar(String s) {
        Map<Character, Boolean> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, !map.containsKey(c));
        }
        for (char c : s.toCharArray()) {
            if (map.get(c))
                return c;
        }
        return ' ';
    }
}

// 2. 有序哈希表
class Solution2 {
    public char firstUniqChar(String s) {
        Map<Character, Boolean> map = new LinkedHashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, !map.containsKey(c));
        }
        for (char c : map.keySet()) {
            if (map.get(c))
                return c;
        }
        return ' ';
    }
}

// 3. 队列
/*
 * 我们也可以借助队列找到第一个不重复的字符。
 * 队列具有「先进先出」的性质，因此很适合用来找出第一个满足某个条件的元素。
 * 具体地，我们使用与方法二相同的哈希映射，并且使用一个额外的队列，按照顺序存储每一个字符以及它们第一次出现的位置。
 * 当我们对字符串进行遍历时，设当前遍历到的字符为 c：
 * 1. 如果 c 不在哈希映射中，我们就将 c 与它的索引作为一个二元组放入队尾
 * 2. 否则我们就需要检查队列中的元素是否都满足「只出现一次」的要求，即我们不断地根据哈希映射中存储的值（是否为
 * −1）选择弹出队首的元素，直到队首元素「真的」只出现了一次或者队列为空。
 * 在遍历完成后，如果队列为空，说明没有不重复的字符，返回空格，否则队首的元素即为第一个不重复的字符以及其索引的二元组。
 * 在维护队列时，我们使用了「延迟删除」这一技巧。
 * 也就是说，即使队列中有一些字符出现了超过一次，但它只要不位于队首，那么就不会对答案造成影响，我们也就可以不用去删除它。
 * 只有当它前面的所有字符被移出队列，它成为队首时，我们才需要将它移除。
 */
class Solution3 {
    public char firstUniqChar(String s) {
        Map<Character,Integer> map = new LinkedHashMap<>();
        Queue<Pair> queue = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, i);
                queue.offer(new Pair(c,i));
            } else {
                map.put(c, -1);
                while (!queue.isEmpty() && map.get(queue.peek().ch) == -1) {
                    queue.poll();
                }
            }   
        }
        return queue.isEmpty() ? ' ' : queue.poll().ch;
    }

    class Pair {
        char ch;
        int pos;

        Pair(char ch, int pos) {
            this.ch = ch;
            this.pos = pos;
        }
    }
}
/* 
 * 复杂度分析
 * 时间复杂度：O(n)，其中 n 是字符串 s 的长度。遍历字符串的时间复杂度为 O(n)，而在遍历的过程中我们还维护了一个队列，由于每一个字符最多只会被放入和弹出队列最多各一次，因此维护队列的总时间复杂度为 O(∣Σ∣)，由于 s 包含的字符种类数一定小于 s 的长度，因此 O(∣Σ∣) 在渐进意义下小于 O(n)，可以忽略。
 * 空间复杂度：O(∣Σ∣)，其中 Σ 是字符集，在本题中 s 只包含小写字母，因此 ∣Σ∣≤26。我们需要 O(∣Σ∣) 的空间存储哈希映射以及队列。
 */