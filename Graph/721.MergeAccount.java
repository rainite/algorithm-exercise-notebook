/**
Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:
Input: 
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
Explanation: 
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
Note:

The length of accounts will be in the range [1, 1000].
The length of accounts[i] will be in the range [1, 10].
The length of accounts[i][j] will be in the range [1, 30].

思考: 典型图的题, 主要就是找到node, 先分析题意叫合并account, 怎么合并, 有相同的email就合并
那么意思email这个集合里, 如果有元素和另一个账号email集合里元素相同,就合并
所以可以发现, 最后合并成功一个名字里, 有所有相关联的emails, 
那么就以email为node建图, 边为若2个email有是同一个人的, 就建立边
又因为email是唯一的, 那么再建一个email -> 名字的map, 根据这个map来遍历(作为入口做dfs), 就可以遍历到所有的node

遍历部分, 用visited做全局记录, dfs不需要特别的base case, 遍历完了就结束
 */

class Solution {
  public List<List<String>> accountsMerge(List<List<String>> accounts) {
    List<List<String>> res = new LinkedList<>();
    Map<String, String> emailToName = new HashMap<>();
    Map<String, Set<String>> emailToNei = new HashMap<>();

    // build graph
    for (List<String> list : accounts) {
      String name = list.get(0);
      for (int i = 1; i < list.size(); i++) {
        emailToName.put(list.get(i), name);
        if (!emailToNei.containsKey(list.get(i))) {
          emailToNei.put(list.get(i), new HashSet<String>());
        }
        if (i == 1) {
          continue;
        } else {
          emailToNei.get(list.get(i)).add(list.get(i - 1));
          emailToNei.get(list.get(i - 1)).add(list.get(i));
        }
      }
    }

    //combine the emails
    Set<String> visited = new HashSet<>();
    for (String email : emailToName.keySet()) {
      if (visited.add(email)) {
        List<String> connectedEmails = new LinkedList<>();
        connectedEmails.add(email);

        dfs(emailToNei, connectedEmails, email, visited);

        Collections.sort(connectedEmails);
        connectedEmails.add(0, emailToName.get(email));
        res.add(connectedEmails);
      }
    }
    return res;
  }

  private void dfs(Map<String, Set<String>> emailToNei, List<String> connectedEmails,
                   String email, Set<String> visited) {

    for (String neighbour : emailToNei.get(email)) {
      if (visited.add(neighbour)) {    
        connectedEmails.add(neighbour);
        dfs(emailToNei, connectedEmails, neighbour, visited);
      }
    }
  }
}