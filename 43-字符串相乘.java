// 1. 模拟竖式
class Solution1 {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        String res = "0";
        int m = num1.length(), n = num2.length();
        for (int i = m - 1; i >= 0; i--) {
            StringBuffer sb = new StringBuffer();
            // num2 除了第一位的其他位与 num1 运算的结果需要补 0
            for (int j = 0; j < m - 1 - i; j++) {
                sb.append('0');
            }
            int a = num1.charAt(i) - '0';
            int carry = 0;
            for (int j = n - 1; j >= 0; j--) {
                int b = num2.charAt(j) - '0';
                int product = a * b + carry;
                sb.append(product % 10);
                carry = product / 10;
            }
            if (carry != 0)
                sb.append(carry);
            res = add(res, sb.reverse().toString());
        }
        return res;
    }

    public String add(String num1, String num2) {
        StringBuffer sb = new StringBuffer();
        int carry = 0;
        int i = num1.length() - 1, j = num2.length() - 1;
        while (i >= 0 || j >= 0 || carry > 0) {
            int a = i >= 0 ? num1.charAt(i) - '0' : 0;
            int b = j >= 0 ? num2.charAt(j) - '0' : 0;
            int res = a + b + carry;
            sb.append(res % 10);
            carry = res / 10;
            i -= 1;
            j -= 1;
        }
        sb.reverse();
        return sb.toString();
    }
}

// 2. 优化竖式
class Solution2 {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int[] res = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                int a = num1.charAt(i) - '0';
                int b = num2.charAt(j) - '0';
                int sum = res[i + j + 1] + a * b;
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < res.length; i++) {
            if (sb.length() == 0 && res[i] == 0) 
                continue;
            sb.append(res[i]);
        }
        return sb.toString();
    }
}
