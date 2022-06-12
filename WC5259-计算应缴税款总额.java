class Solution {
    public double calculateTax(int[][] brackets, int income) {
        double tax = 0;
        int i = 0;
        int lastUpperLevel = 0;
        while (income > brackets[i][0]) {
            // **此处注意不能写成 (double) (brackets[i][1] / 100)
            tax += (brackets[i][0] - lastUpperLevel) * (double) brackets[i][1] / 100; 
            lastUpperLevel = brackets[i][0];
            i++;
        }
        tax += (income - lastUpperLevel) * (double) brackets[i][1] / 100;
        return tax;
    }
}