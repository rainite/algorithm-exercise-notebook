import java.util.*;

/**
 * public class GraphNode {
 *   public int key;
 *   public List<GraphNode> neighbors;
 *   public GraphNode(int key) {
 *     this.key = key;
 *     this.neighbors = new ArrayList<GraphNode>();
 *   }
 * }
 */

 /*检查整个图是否为二分图
 思路：若为二分图，那么从区域A的点出去的所有点都在B区域，然后B区域连接的所有点都在A，所以我们可以做一个染色，
 如果A是0，那么他的邻居就应该全是1，反之亦然，用BFS可解
 找到算法后，我们需要对所有点都做一次BFS，因为可能有孤立的点，用visited记录访问过的点和颜色，若有冲突则false
 */
public class Solution {
    public boolean isBipartite(List<GraphNode> graph) {
        // assume the list is non-empty
        //Track the the visited nodes, use integer 0,1 to dye nodes
        Map<GraphNode,Integer> visited = new HashMap<>();
        for (GraphNode node : graph) {
            if (!BFS(node,visited)) {
                return false;
            }
        }
        return true;
    }

    private boolean BFS (GraphNode node, Map<GraphNode,Integer> visited) {
        if (visited.containsKey(node)) {
            return true;
        }
        Queue<GraphNode> queue = new LinkedList<>();
        queue.add(node);
        // 想清楚这个地方，此时这个点一定是没有被检查过的
        visited.add(node,0);
        while (!queue.isEmpty()) {
            GraphNode cur = queue.remove();
            // Neighbors should have a different color 
            int color = visited.get(cur) == 0 ? 1 : 0;
            for (GraphNode neighbor : cur.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor,color);
                    queue.add(neighbor);
                } else {
                    if (visited.get(neighbor) != color) {
                        return false;
                    }
                }
            }
        }
        return true;
        
    }
}