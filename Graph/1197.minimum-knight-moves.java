/*
 * @lc app=leetcode id=1197 lang=java
 *
 * [1197] Minimum Knight Moves
 *
 思路很直接，因为是求到目标点的最短距离，又是在直角坐标系下，选择A*算法,
 A*与Dijkstra区别：算法综合了最良優先搜索和Dijkstra算法的优点：在进行启发式搜索提高算法效率的同时，可以保证找到一条最优路径（基于评估函数）。
 评估函数 F = G + H, 如果以 g(n)表示从起点到任意顶点 n 的实际距离， h(n)表示任意顶点 n 到目标顶点的估算距离（根据所采用的评估函数的不同而变化）
    如果 g(n)为0，即只计算任意顶点 n到目标的评估函数 h(n)，而不计算起点到顶点 n的距离，则算法转化为使用贪心策略的最良優先搜索，速度最快，但可能得不出最优解；
    如果 h(n)不大于顶点 n到目標頂點的實際距離，则一定可以求出最优解，而且 h(n)越小，需要计算的节点越多，算法效率越低，常见的评估函数有——欧几里得距离、曼哈顿距离、切比雪夫距离；
    如果 h(n)为0，即只需求出起点到任意顶点 n的最短路径 g(n)，而不计算任何评估函数 h(n)，则转化为单源最短路径问题，即Dijkstra算法，此时需要计算最多的顶点；

或者我们可以理解为：
    G = 从起点 A 移动到指定方格的移动代价，这里用Steps
    H = 从指定的方格移动到终点 B 的估算成本。这里用估算步数，即 曼哈顿距离 / 3 （马每跳一次走3的距离）

有 F = steps + (abs(x - x1) + abs(y - y1)) / 3 ,放入PQ遍历解决

小 tips： 因为题目说无限大棋盘，以马坐标为原点，意思目标坐标点可正可负，这里直接取abs，因为4个象限都一样，不影响最终结果
注意与普通的BFS不一样，这里不能在遍历放Queue的时候就加visited，因为有value这个因素，要真正从queue里取出来了，才能说这个结果确定了，确保取出的点是现在value最小的点
*/
class Solution {
    private int TARGET_X;
    private int TARGET_Y;
    class Node {
        int x;
        int y;
        int steps;
        int value;
        public Node(int x, int y, int steps) {
            this.x = x;
            this.y = y;
            this.steps = steps;
            calValue();
        }
        private void calValue() {
            this.value = steps + (Math.abs(this.x - TARGET_X) + Math.abs(this.y - TARGET_Y)) / 3;
        } 
    }
    public int minKnightMoves(int x, int y) {
        this.TARGET_X = Math.abs(x);
        this.TARGET_Y = Math.abs(y);
        int[][] visited = new int[TARGET_X + 2][TARGET_Y + 2];
        int[][] dir = {{1,2},{-1,2},{1,-2},{-1,-2},{2,1},{-2,1},{2,-1},{-2,-1}};
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> a.value - b.value);
        Node start = new Node(0, 0, 0);
        pq.add(start);
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visited[cur.x][cur.y] == 1) {
                continue;
            }
            if (cur.x == TARGET_X && cur.y == TARGET_Y) {
                return cur.steps;
            }
            visited[cur.x][cur.y] = 1;
            for (int[] d : dir) {
                int nextX = cur.x + d[0];
                int nextY = cur.y + d[1];
                if (nextX < 0 || nextX >= visited.length || nextY < 0 || nextY >= visited[0].length || visited[nextX][nextY] == 1) {
                    continue;
                }
                Node newNode = new Node(nextX, nextY, cur.steps + 1);
                pq.add(newNode);
            }
        }
        return -1;
    }
}

