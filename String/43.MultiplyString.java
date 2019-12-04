/**
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Example 1:

Input: num1 = "2", num2 = "3"
Output: "6"
Example 2:

Input: num1 = "123", num2 = "456"
Output: "56088"
Note:

The length of both num1 and num2 is < 110.
Both num1 and num2 contain only digits 0-9.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
You must not use any built-in BigInteger library or convert the inputs to integer directly.

思路： 
1. 首先确定答案位数， 99 * 99 就是4位， 所以位数一定是len(a) + len(b)
2. 再确定每个数字乘出来应该放在答案哪儿，两个数乘就两个位置，index(从左到右)的[i+j, i+j+1]
3. 乘出来的数，加上对应的‘个’位数字(可以是进了位的十位数)， 然后放到index里，这里注意个位只能用 = ，十位部分要用+，
因为个位我们是取出来计算过的
 */
public String multiply(String num1, String num2) {
  int m = num1.length(), n = num2.length();
  int[] pos = new int[m + n];
   
  for(int i = m - 1; i >= 0; i--) {
    for(int j = n - 1; j >= 0; j--) {
      int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0'); 
      int p1 = i + j, p2 = i + j + 1;
      int sum = mul + pos[p2];

      pos[p1] += sum / 10;
      pos[p2] = (sum) % 10;
    }
  }  
    
  StringBuilder sb = new StringBuilder();
  for(int p : pos) {
    if(!(sb.length() == 0 && p == 0)) {
      sb.append(p);
    }
  }
  return sb.length() == 0 ? "0" : sb.toString();
}