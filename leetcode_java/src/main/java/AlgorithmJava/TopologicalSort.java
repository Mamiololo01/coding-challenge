package AlgorithmJava;

import java.util.*;

public class TopologicalSort {

    // V1
    // (generated by GPT)
    /**
     *  TopologicalSort STEP
     *
     *   (BFS)
     *
     *   Step 1) build graph, (and topoOrder, index)
     *   Step 2) find degree = 0 node, put into queue
     *   Step 3) pop node from queue, find its neighbor, make degree -= 1,
     *           if degree = 0 node, put into queue
     *   Step 4) repeat  Step 1) -  Step 3)
     *
     *
     */
    /**
     *
     * Kahn's Algorithm for Topological Sort (with BFS)
     *
     * 1) Identify nodes with no incoming edges and add them to the queue.
     *
     * 2) While the queue is not empty:
     *     - Remove a node from the queue and add it to the topological order.
     *     - For each outgoing edge from that node, reduce the in-degree of the target node by 1.
     *     - If the in-degree of the target node becomes 0, add it to the queue.
     *
     * 3) If the number of nodes added to the topological order is less
     *    than the total number of nodes, the graph has at least one cycle,
     *    and a topological sort is not possible.
     *
     */
    public int[] topologicalSort(int numCourses, int[][] prerequisites) {

        // Step 1: Build the graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        int[] inDegree = new int[numCourses];
        for (int[] prereq : prerequisites) {

            /**
             *  NOTE !!!
             *
             *   given prereq = [dest, src]
             *
             *   -> the dependency ordering:
             *
             *      src first, then dest
             *
             *
             *   Example :
             *
             *    int[][] prerequisites = { {1, 0}, {2, 0}, {3, 1}, {3, 2} };
             *
             *    -> 0 is a prerequisite for 1 and 2.
             *    -> 1 and 2 are prerequisites for 3.
             *
             */
            // int dest = prereq[0];: dest represents the course that depends on src.
            int dest = prereq[0];
            // int src = prereq[1];: src represents the course that is a prerequisite.
            int src = prereq[1];
            graph.get(src).add(dest);
            /**
             *  Example:
             *
             *
             *  For the pair {1, 0}:
             *
             * 	- src = 0, dest = 1
             * 	- graph.get(0).add(1) adds 1 to the adjacency list of 0.
             * 	- inDegree[1]++ increments the in-degree of 1 by 1.
             *
             *
             *  For the pair {2, 0}:
             *
             * 	- src = 0, dest = 2
             * 	- graph.get(0).add(2) adds 2 to the adjacency list of 0.
             * 	- inDegree[2]++ increments the in-degree of 2 by 1.
             *
             *
             * 	 For the pair {3, 1}:
             *
             * 	- src = 1, dest = 3
             * 	- graph.get(1).add(3) adds 3 to the adjacency list of 1.
             * 	- inDegree[3]++ increments the in-degree of 3 by 1.
             *
             */
            inDegree[dest]++; // update the "dependency count" of dest node
        }

        // Step 2: Initialize the queue with nodes having in-degree of 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            // NOTE !!! ONLY put node into queue when its "degree" == 0
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // Step 3: Perform Kahn's algorithm
        /**
         *
         *
         * "topoOrder" is an array that will hold the nodes in their topologically sorted order.
         *
         * "index" keeps track of the current position in this array where the next node should be placed.
         *
         *
         */
        int[] topoOrder = new int[numCourses];
        int index = 0;

        while (!queue.isEmpty()) {
            /**
             * NOTE !!!
             *
             *  since only  "degree" == 0 node was put into queue,
             *  so here, all pop node are with "degree" == 0
             *  so they are already visited, and it's OK to add them to the result array (topoOrder)
             */
            int node = queue.poll();
            topoOrder[index++] = node;

            // Reduce the in-degree of adjacent nodes by 1
            /**
             *  NOTE !!!
             *
             *   visit node's neighbor,
             *   make it degree -= 1
             *   and if degree == 0, put such node (neighbor) to queue
             */
            for (int neighbor : graph.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // Check if a valid topological order is possible
        // NOTE !!!  if index != numCourses -> graph has a cycle, so it's NOT possible to perform topological sort on it
        if (index != numCourses) {
            return new int[0]; // Return an empty array if the graph has a cycle
        }

        return topoOrder;
    }

    // test
    public static void main(String[] args) {

        TopologicalSort topoSort = new TopologicalSort();
        int numCourses = 4;
        // Directed Acyclic Graph (DAG) where each directed edge u -> v means that u comes before v.
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};

        int[] result = topoSort.topologicalSort(numCourses, prerequisites);
        System.out.println("Topological Order: " + Arrays.toString(result));
    }

}