/**
 * You are given a 0-indexed string expression of the form "<num1>+<num2>" where <num1> and <num2> represent positive integers.

Add a pair of parentheses to expression such that after the addition of parentheses, expression is a valid mathematical expression and evaluates to the smallest possible value. The left parenthesis must be added to the left of '+' and the right parenthesis must be added to the right of '+'.

Return expression after adding a pair of parentheses such that expression evaluates to the smallest possible value. If there are multiple answers that yield the same result, return any of them.

The input has been generated such that the original value of expression, and the value of expression after adding any pair of parentheses that meets the requirements fits within a signed 32-bit integer.

 

Example 1:

Input: expression = "247+38"
Output: "2(47+38)"
Explanation: The expression evaluates to 2 * (47 + 38) = 2 * 85 = 170.
Note that "2(4)7+38" is invalid because the right parenthesis must be to the right of the '+'.
It can be shown that 170 is the smallest possible value.
Example 2:

Input: expression = "12+34"
Output: "1(2+3)4"
Explanation: The expression evaluates to 1 * (2 + 3) * 4 = 1 * 5 * 4 = 20.

Constraints:

3 <= expression.length <= 10
expression consists of digits from '1' to '9' and '+'.
expression starts and ends with digits.
expression contains exactly one '+'.
The original value of expression, and the value of expression after adding any pair of parentheses that meets the requirements fits within a signed 32-bit integer.

 * 思路: 流水线处理问题,想清楚每一步要做什么:
 * 1. 根据加号拆开 
 * 2. 遍历添加括号
 *  2.1 拆开的左右两边式子括号添加规则不一样,需要特殊处理括号在一头一尾的情况
 * 3. 根据括号位置组成新的表达式,计算大小
 * 4. 最小的还原成输出答案
 * 
 * 思路完成,记得string 可做split,可substring, 可与integer直接转换,就不需要转换为char[] 做array的运算了
 */
class Solution {
    public String minimizeResult(String expression) {
        String[] nums = expression.split("[+]");
        String left = nums[0];
        String right = nums[1];
        return calculate(left, right);
    }

    private String calculate (String left, String right) {
        String res = "(" + left + "+" + right + ")";
        int min = Integer.parseInt(left) + Integer.parseInt(right);
        for (int i = 0; i < left.length(); i++) {
            int a = i == 0 ? 1 : Integer.parseInt(left.substring(0, i));
            int b = Integer.parseInt(left.substring(i));
            for (int j = right.length(); j > 0; j--) {
                int c = Integer.parseInt(right.substring(0, j));
                int d = j == right.length() ? 1 : Integer.parseInt(right.substring(j));

                int temp = a * (b + c) * d;
                if (temp < min) {
                    res = left.substring(0,i) + "(" + left.substring(i) + "+" +
                            right.substring(0,j) + ")" + right.substring(j);
                    min = temp;
                }
            }
        }
        return res;
    }
    
}