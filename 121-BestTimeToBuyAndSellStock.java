
// 1. 一次遍历
class Solution1 {
    public int maxProfit(int[] prices) {
        int minPriceSoFar = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            minPriceSoFar = price < minPriceSoFar ? price : minPriceSoFar;
            int profit = price - minPriceSoFar;
            maxProfit = profit > maxProfit ? profit : maxProfit;
        }
        return maxProfit;
    }
}

