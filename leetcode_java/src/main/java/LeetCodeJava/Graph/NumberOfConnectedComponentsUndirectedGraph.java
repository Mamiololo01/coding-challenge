package LeetCodeJava.Graph;

// https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/description/?envType=list&envId=xoqag3yj
// https://leetcode.ca/all/323.html
// https://leetcode.ca/2016-10-18-323-Number-of-Connected-Components-in-an-Undirected-Graph/

import java.util.*;

public class NumberOfConnectedComponentsUndirectedGraph {

    // V0
    // IDEA : GRAPH
    // TODO : implement it
//    public int countComponents(int n, int[][] edges) {
//        return 0;
//    }

    // V1
    // IDEA : UNION FIND
    // https://leetcode.ca/2016-10-18-323-Number-of-Connected-Components-in-an-Undirected-Graph/
    private int[] p;

    public int countComponents_1(int n, int[][] edges) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            p[find(a)] = find(b);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i == find(i)) {
                ++ans;
            }
        }
        return ans;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    // V2
    // IDEA : DFS
    // https://www.cnblogs.com/cnoodle/p/14197652.html
    public int countComponents_2(int n, int[][] edges) {
        int count = 0;
        List<List<Integer>> g = new ArrayList<>();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            g.get(e[0]).add(e[1]);
            g.get(e[1]).add(e[0]);
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                count++;
                dfs(visited, i, g);
            }
        }
        return count;
    }

    private void dfs(boolean[] visited, int node, List<List<Integer>> g) {
        visited[node] = true;
        for (int next : g.get(node)) {
            if (!visited[next]) {
                dfs(visited, next, g);
            }
        }
    }

    // V3
    // IDEA :  UNION FIND
    // https://www.cnblogs.com/cnoodle/p/14197652.html
    public int countComponents_3(int n, int[][] edges) {
        int count = n;
        int[] parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        for (int[] edge : edges) {
            int p = find(parents, edge[0]);
            int q = find(parents, edge[1]);
            if (p != q) {
                parents[p] = q;
                count--;
            }
        }
        return count;
    }

    private int find(int[] parents, int i) {
        while (parents[i] != i) {
            parents[i] = parents[parents[i]]; // route compression
            i = parents[i];
        }
        return i;
    }

    // V4
    // IDEA :  BFS
    // https://www.cnblogs.com/cnoodle/p/14197652.html
    public int countComponents_4(int n, int[][] edges) {
        int count = 0;
        List<List<Integer>> g = new ArrayList<>();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            g.get(e[0]).add(e[1]);
            g.get(e[1]).add(e[0]);
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                count++;
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int index = queue.poll();
                    visited[index] = true;
                    for (int next : g.get(index)) {
                        if (!visited[next]) {
                            queue.offer(next);
                        }
                    }
                }
            }
        }
        return count;
    }

}