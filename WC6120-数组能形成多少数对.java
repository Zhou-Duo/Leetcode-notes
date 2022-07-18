class Solution {
    public int[] numberOfPairs(int[] nums) {
        int[] counts = new int[101];
        int pairs = 0, integers = 0;
        for (int num : nums) {
            counts[num] += 1;
        }
        for (int count : counts) {
            pairs += count / 2;
            integers += count % 2;
        }
        return new int[] { pairs, integers };
    }
}