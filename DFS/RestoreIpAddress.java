/*
Given a string containing only digits, restore it by returning all possible valid IP address combinations.

Example:

Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]

思路: 首先这个题corner case 不一样, length 在[0,12]之间才有效
1. level (4层) 元素可以1 ~ 3个
2. status (3个) 每一层在index为1,2,3取substring作为一个IP地址段
*/
class Solution {
  public List<String> restoreIpAddresses(String ip) {
    List<String> res  = new ArrayList<>();
    if (ip == null || ip.length() == 0 || ip.length() > 12) {
      return res;
    }
    helper(res, new StringBuilder(), ip, 0, 0);
    return res;
  }
  private void helper(List<String> res, StringBuilder sb, String ip, int index, int dots) {
    if (dots == 4) {
      if (index == ip.length()) {
        res.add(sb.toString());
      }
      return;
    }
    // 3个status
    for (int i = 1; i < 4 && index + i <= ip.length(); i++) {
      String num = ip.substring(index, index + i);
      // 验证数据是否合理, 注意0.0.0.0合理, 但是 01.1.1.1不合理
      if (num.length() > 1 && num.charAt(0) == '0' || Integer.parseInt(num) > 255) {
        break;
      }
      int size = num.length();
      if (sb.length() == 0) {
        sb.append(num);
      } else {
        sb.append("." + num);
        size++;
      }
      helper(res, sb, ip, index + i, dots + 1);
      sb.setLength(sb.length() - size);
    }
  }
}