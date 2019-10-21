/**
Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

Note:

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.

String 操作的好题, 这里特殊点在于while 里是只要有某一个string 没走完就不能退出while逻辑
 */
class Solution {
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        StringBuilder res = new StringBuilder();
        int carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
            int first = i >= 0 ? num1.charAt(i) - '0' : 0;
            int last = j >= 0 ? num2.charAt(j) - '0' : 0;
            int temp = first + last + carry;
            carry = 0;
            if (temp > 9) {
                carry = 1;
            }
            res.append(temp % 10);
            i--; j--;
        }

        return res.reverse().toString();
    }
}