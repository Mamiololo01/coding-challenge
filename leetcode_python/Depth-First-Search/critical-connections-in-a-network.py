"""

1192. Critical Connections in a Network
Hard

There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can reach other servers directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some servers unable to reach some other server.

Return all critical connections in the network in any order.

 

Example 1:


Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.
Example 2:

Input: n = 2, connections = [[0,1]]
Output: [[0,1]]
 

Constraints:

2 <= n <= 105
n - 1 <= connections.length <= 105
0 <= ai, bi <= n - 1
ai != bi
There are no repeated connections.

"""

# V0

# V1
# IDEA : DFS
# IDEA : Inspired by tarjan, but simplified to just do what is requested in a problem, i.e. find the critical paths (and not strongly connected components):
# https://leetcode.com/problems/critical-connections-in-a-network/discuss/1082197/Python-DFS
class Solution(object):
    def criticalConnections(self, n, connections):

        cons = defaultdict(set)
        for a, b in connections:
            cons[a].add(b)
            cons[b].add(a)

        low = {}
        results = []
        def visit(node, from_node=None):
            if node in low: return low[node]
            cur_id = low[node] = len(low)

            for neigh in cons[node]:
                if neigh == from_node: continue
                low[node] = min(low[node], visit(neigh, node))

            if cur_id == low[node] and from_node is not None:
                results.append([from_node, node])
            return low[node]

        visit(0)
        return results

# V1'
# IDEA : GRAPH
# https://leetcode.com/problems/critical-connections-in-a-network/discuss/571964/Python-Solution
class Solution(object):
        def criticalConnections(self, n, connections):
            def graph(connections):
                from collections import defaultdict
                g = defaultdict(list)
                for u, v in connections:
                    g[u].append(v)
                    g[v].append(u)
                return g

            g = graph(connections)
            n = len(g.keys()) # no of nodes

            self.id = 0
            ids = [0]*(n+1)
            lows = [0]*(n+1)
            visited = set()

            def dfs(at, parent, bridges):
                visited.add(at)
                self.id = self.id + 1
                lows[at] = ids[at] = self.id

                for to in g[at]:
                    if to==parent: continue
                    if to not in visited:
                        dfs(to, at, bridges)
                        lows[at] = min(lows[at], lows[to]) # this is the min id that I can reach 
                        if ids[at]<lows[to]:
                            bridges.append([at, to])
                    else:
                        # since a back edge find the lowest id at can reach 
                        lows[at] = min(lows[at], ids[to])
            bridges = []
            for i in range(n):
                if i not in visited:
                    dfs(i, -1, bridges)

            return bridges

# V1''
# IDEA : tarjans algorithm
# https://leetcode.com/problems/critical-connections-in-a-network/discuss/832441/Python-Tarjans-Implementation
class Solution:
    def criticalConnections(self, n, edges):
        #tarjans algorithm - cut edge (Articulation edges)
        graph = [[] for _ in range(n)]
        for x,y in edges:
            graph[x].append(y)
            graph[y].append(x)
        seen = [False]*n
        low = [None]*n
        time = [None]*n
        t = [0]
        res = []
        
        
        def dfs(node,parent=None):
            seen[node] = True
            low[node] = time[node] = t[0]
            t[0] += 1
    
            for nei in graph[node]:
                if parent == nei:
                    continue
                if seen[nei]:
                    low[node] = min(low[node],time[nei])
                else:
                    dfs(nei,node)
                    low[node ] = min(low[node],low[nei])
                    if low[nei] > time[node]:
                        res.append([node,nei])
                
        for i in range(n):
            if seen[i] == False:
                dfs(i)
        return res

# V2