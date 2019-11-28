/**
S and T are strings composed of lowercase letters. In S, no letter occurs more than once.

S was sorted in some custom order previously. We want to permute the characters of T so that they match the order that S was sorted. More specifically, if x occurs before y in S, then x should occur before y in the returned string.

Return any permutation of T (as a string) that satisfies this property.

Example :
Input: 
S = "cba"
T = "abcd"
Output: "cbad"
Explanation: 
"a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and "a". 
Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are also valid outputs.
 

Note:

S has length at most 26, and no character is repeated in S.
T has length at most 200.
S and T consist of lowercase letters only.

思考: 桶排序! 但是如果不知道长短呢? 比如要排序的是Integer,那最好还是用一个map来记录顺序了
 */
class Solution {
    public String customSortString(String S, String T) {
        StringBuilder sb = new StringBuilder();
        int[] count = new int[26];
        for (char c : T.toCharArray()) {
            count[c -'a']++;
        }
        for (char c : S.toCharArray()) {
            while (count[c - 'a']-- > 0)
                sb.append(c);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            while (count[c - 'a']-- > 0)
                sb.append(c);
        } 
        return sb.toString();
    }
}

/**
Given two integer arrays A1 and A2, sort A1 in such a way that the relative order among the elements will be same as those are in A2.

For the elements that are not in A2, append them in the right end of the A1 in an ascending order.

Assumptions:

A1 and A2 are both not null.
There are no duplicate elements in A2.
Examples:

A1 = {2, 1, 2, 5, 7, 1, 9, 3}, A2 = {2, 1, 3}, A1 is sorted to {2, 2, 1, 1, 3, 5, 7, 9}
 */

class Solution {
  public int[] sortSpecial(int[] A1, int[] A2) {
    // Write your solution here
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < A2.length; i++) {
      map.put(A2[i], i);
    }
    Integer[] a1 = new Integer[A1.length];
    // boxing
    for (int i = 0; i < A1.length; i++) {
      a1[i] = A1[i];
    }
    Arrays.sort(a1, new Comparator<Integer>() {
      public int compare(Integer a, Integer b) {
        Integer indexA = map.get(a);
        Integer indexB = map.get(b);
        if (indexA != null && indexB != null) {
          return indexA.compareTo(indexB);
        }
        if (indexA == null && indexB == null) {
          return a.compareTo(b);
        }
        return indexA == null ? 1 : -1;
      }
    });
    // unboxing
    for (int i = 0; i < a1.length; i++) {
      A1[i] = a1[i];
    }
    return A1;
  }
}