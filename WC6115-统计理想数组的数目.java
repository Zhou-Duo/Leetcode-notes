import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

class Solution1 {
    public static final long MOD = 1000000007;
    public int idealArrays(int n, int maxValue) {
        long[] startArray = new long[maxValue+1];
        // n = 2
        for (int i = 1; i < startArray.length; i++) {
            startArray[i] = (long) Math.floor(maxValue / i);
        }
        while (n > 2) {
            for (int i = 1; i < startArray.length; i++) {
                for (int j = 2*i; j < startArray.length; j+=i) {
                    startArray[i] += startArray[j];
                    startArray[i] %= MOD;
                }
            }
            n -= 1;
        }
        int res = 0;
        for (int i = 1; i < startArray.length; i++) {
            res += startArray[i];
            res %= MOD;
        }
        return res;
    }
}

class Solution2 {
    public static final long MOD = 1000000007;

	public int idealArrays(int n, int maxValue) {
		int sum = 0, dp[][] = new int[14][n + 1];
		for (int i = 0; i <= n; i++) {
			dp[0][i] = 1;
		}
		for (int i = 1; i < 14; i++) {
			dp[i][1] = 1;
			for (int j = 2; j <= n; j++) {
				for (int k = 0; k <= i; k++) {
					dp[i][j] = (dp[i][j] + dp[k][j - 1]) % MOD;
				}
			}
		}
		for (int i = 1, j = 1; i <= maxValue; j = ++i) {
			long prod = 1;
			for (int k = 2; k <= Math.sqrt(i); k++) {
				int count = 0;
				for (; j % k == 0; j /= k, count++) {
				}
				prod = prod * dp[count][n] % MOD;
			}
			if (j > 1) {
				prod = prod * dp[1][n] % MOD;
			}
			sum = (int) (sum + prod) % MOD;
		}
		return sum;
	}
}