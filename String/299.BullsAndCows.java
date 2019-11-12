/**
You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.

Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows. 

Please note that both secret number and friend's guess may contain duplicate digits.

Example 1:

Input: secret = "1807", guess = "7810"

Output: "1A3B"

Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
Example 2:

Input: secret = "1123", guess = "0111"

Output: "1A1B"

Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.

 */
// bull = same number - exact correct
class Solution {
  public String getHint(String secret, String guess) {
    int common = getCommon(secret, guess);
    int same = getSame(secret, guess);
    return same + "A" + (common - same) + "B";
  }
  private int getCommon(String secret, String guess) {
    int count = 0;
    Map<Character, Integer> map = new HashMap<>();
    for (char c : secret.toCharArray()) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }
    for (char c : guess.toCharArray()) {
      if (map.containsKey(c) && map.get(c) > 0) {
        count++;
        map.put(c, map.get(c) - 1);
      }
    }
    return count;
  }
  private int getSame(String secret, String guess) {
    int count = 0;
    for (int i = 0; i < secret.length(); i++) {
      if (secret.charAt(i) == guess.charAt(i)) {
        count++;
      }
    }
    return count;
  }
}

/* 
one pass 版本 用数组记录个数 
secret 遍历一个数就计数++, 并且若如果这个次数是负数, 说明之前被guess消耗过, cows++
guess 遍历一个数就--, 代表消耗一个数,若这个数 > 0, 说明之前被secret增加过, cows++
*/
class Solution {
  public String getHint(String secret, String guess) {
    int bulls = 0;
    int cows = 0;
    //0 ~ 9
    int[] numbers = new int[10];
    for (int i = 0; i < secret.length(); i++) {
      if (secret.charAt(i) == guess.charAt(i)) {
        bulls++;
      } else {
        int secNum = secret.charAt(i) - '0';
        int gueNum = guess.charAt(i) - '0';
        if (numbers[secNum] < 0) {
          cows++;
        }
        if (numbers[gueNum] > 0) {
          cows++;
        }
        numbers[secNum]++;
        numbers[gueNum]--;
      }
    }
    return bulls + "A" + cows + "B";
  }
}