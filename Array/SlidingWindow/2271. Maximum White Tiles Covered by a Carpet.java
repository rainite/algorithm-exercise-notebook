/**
You are given a 2D integer array tiles where tiles[i] = [li, ri] represents that every tile j in the range li <= j <= ri is colored white.

You are also given an integer carpetLen, the length of a single carpet that can be placed anywhere.

Return the maximum number of white tiles that can be covered by the carpet.

 

Example 1:


Input: tiles = [[1,5],[10,11],[12,18],[20,25],[30,32]], carpetLen = 10
Output: 9
Explanation: Place the carpet starting on tile 10. 
It covers 9 white tiles, so we return 9.
Note that there may be other places where the carpet covers 9 white tiles.
It can be shown that the carpet cannot cover more than 9 white tiles.
Example 2:


Input: tiles = [[10,11],[1,1]], carpetLen = 2
Output: 2
Explanation: Place the carpet starting on tile 10. 
It covers 2 white tiles, so we return 2.
 

Constraints:

1 <= tiles.length <= 5 * 104
tiles[i].length == 2
1 <= li <= ri <= 109
1 <= carpetLen <= 109
The tiles are non-overlapping.

思路:
2 pointer, 控制这个carpet的左右pointer挪动,这个挪动只有可能在数组的端点上([0]或[1]上),每次循环判断挪动一次左pointer
或者右pointer,计算covered,取一次最大值,直到右pointer到数组底
!!想清楚物理意义再写条件!!
 */

class Solution {
    public int maximumWhiteTiles(int[][] tiles, int len) {
        Arrays.sort(tiles, Comparator.comparingInt(a -> a[0]));
        int leftIndex = 0;
        int res = 0;
        int covered = 0;

        // 注意退出条件
        for (int rightIndex = leftIndex; leftIndex <= rightIndex && rightIndex < tiles.length;) {
            // corner case: len长度小于第一个区间
            if (len <= tiles[leftIndex][1] - tiles[leftIndex][0] + 1) {
                return len;
            }
            // 2种情况讨论
            // 挪右pointer, 发现毯子超过了当前白块的右端
            if (tiles[leftIndex][0] + len - 1 > tiles[rightIndex][1]) {
                covered += tiles[rightIndex][1] - tiles[rightIndex][0] + 1;
                res = Math.max(res, covered);
                rightIndex++;
            }
            // 挪左pointer, 2种情况，1是毯子还没到这一组白块的左端，2是毯子超过了白块的左端但没超过右端
            else {
                int partial = Math.max(0, tiles[leftIndex][0] + len - tiles[rightIndex][0]);
                res = Math.max(res, covered + partial);
                covered -= tiles[leftIndex][1] - tiles[leftIndex][0] + 1;
                leftIndex++;
            }
        }

        return res;
    }
}