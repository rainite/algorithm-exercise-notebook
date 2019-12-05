/**
Given a sequence of words, check whether it forms a valid word square.

A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).

Note:
The number of words given is at least 1 and does not exceed 500.
Word length will be at least 1 and does not exceed 500.
Each word contains only lowercase English alphabet a-z.
Example 1:

Input:
[
  "abcd",
  "bnrt",
  "crmy",
  "dtye"
]
矩阵小模板，关注坐标变换，i是row, j是column，colum遍历的长度应该是i的row单词的长度
然后确保有意义的判别式也很重要
 */
public class Solution {
  public boolean validWordSquare(List<String> words) {
    if(words == null || words.size() == 0){
      return true;
    }
    int n = words.size();
    for(int i = 0; i < n; i++){
      for(int j = 0; j < words.get(i).length(); j++){
        // 前两个条件就关注后面那个等式， 首先确保words.get(j)有意义，再确保charAt(i)有意义
        if(j >= n || words.get(j).length() <= i || words.get(j).charAt(i) != words.get(i).charAt(j)) {
          return false;
        }
      }
    }
    return true;
  }
}