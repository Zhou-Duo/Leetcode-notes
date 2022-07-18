
class Solution {
	public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
		int[] result = new int[queries.length];
		for (int i = 0; i < queries.length; i++) {
			Integer index[] = new Integer[nums.length], k = nums[0].length() - queries[i][1];
			for (int j = 0; j < nums.length; j++) {
				index[j] = j;
			}
			Arrays.sort(index, (a, b) -> nums[a].substring(k).compareTo(nums[b].substring(k)));
            
			result[i] = index[queries[i][0] - 1];
		}
		return result;
	}
}