package LeetCodeJava.BFS;

//  https://leetcode.com/problems/course-schedule/

import java.util.*;

public class CourseSchedule {

    // V0
    // IDEA ; GRAPH + RECURSION
//    public boolean canFinish(int numCourses, int[][] prerequisites) {
//
//        if (prerequisites == null){
//            return true;
//        }
//
//        // build graph
//        HashMap<Integer, Object> map = new HashMap<>();
//        for (int[] item : prerequisites){
//            int cur = item[0];
//            int prior = item[1];
//            if (!map.containsKey(cur)){
//                List<Integer> tmp = new ArrayList<>();
//                tmp.add(prior);
//                map.put(cur, tmp);
//            }else{
//                List<Integer> tmp = (List<Integer>) map.get(cur);
//                tmp.add(prior);
//                map.put(cur, tmp);
//            }
//        }
//
//        // dfs (check)
//        // status : { 0 : not yet, 1 : ing, 2 : finished }
//        for (int i = 0; i < numCourses; i++){
//            if (!check(map, 0, 0)){
//                return false;
//            }
//        }
//
//        return true;
//    }
//
//    private Boolean check(HashMap<Integer, Object> map, int cur, int status){
//        if (status == 0){
//            //return true;
//            int[] prior = (int[]) map.get(cur);
//            for (int i : prior){
//                if (map.containsKey(cur)){
//                    return false;
//                }
//                if (((int[]) map.get(cur)).length == 0){
//                    return true;
//                }
//                status = 1;
//                return check(map, i, status);
//            }
//        }
//        else if(status == 1){
//            if (!map.containsKey(cur)){
//                status = 2;
//                return true;
//            }else{
//                return false;
//            }
//        }
//
//        return true;
//    }

    // V0'
    // IDEA : BFS
    // NOTE !!! we have 3 loop : numCourses, prerequisites, numCourses
    public boolean canFinish_(int numCourses, int[][] prerequisites) {

        // save course - prerequisites info
        Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
        // save "degree" for every course
        int[] indegree = new int[numCourses];
        // for BFS
        Queue<Integer> queue = new LinkedList<Integer>();
        // check how many courses are NOT yet visited
        int count = numCourses;

        /** NOTE !!! numCourses
         *
         *   -> construct map (course - prerequisites info)
         */
        for (int i = 0; i < numCourses; i++) {
            map.put(i, new ArrayList<Integer>());
        }

        /** NOTE !!! prerequisites
         *
         *   -> 1) inset info to map (course - prerequisites info)
         *   -> 2) insert degree for every course
         *
         */
        for (int i = 0; i < prerequisites.length; i++) {
            map.get(prerequisites[i][0]).add(prerequisites[i][1]);

            /** NOTE : below approaches has same effect */
            // V1
            //indegree[prerequisites[i][1]]++;
            // V2
            indegree[prerequisites[i][1]] += 1;
        }

        /** NOTE !!! numCourses
         *
         *  -> via this loop, we insert elements for "first" visiting
         *     (degree == 0)
         */
        for (int i = 0; i < numCourses; i++) {
            // only insert to queue if "indegree == 0" (course has NO prerequisite)
            if (indegree[i] == 0) {
                queue.offer(i); // insert to queue
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll(); // pop from queue
            for (int i : map.get(current)) {

                /** NOTE : below approaches has same effect */
                // V1
//                if (--indegree[i] == 0) {
//                    queue.offer(i);
//                }

                // V2
                // NOTE !!! if "degree" == 1, then we insert it to queue,
                //          for next visiting
                if (indegree[i] == 1) {
                    queue.offer(i);
                } else {
                    // NOTE !!! if if "degree" != 1,
                    // minus its degree (indegree[i] -= 1), since current degree (other element) is visited
                    indegree[i] -= 1;
                }

            }
            // when finish a course checking ( course visit and course prerequisite visit)
            count--;
        }
        return count == 0;
    }

    // V0'''
    // IDEA : DFS
    // https://github.com/yennanliu/CS_basics/blob/master/leetcode_python/Breadth-First-Search/course-schedule.py
    public boolean canFinish_0_2(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            graph.computeIfAbsent(prerequisite[0], k -> new ArrayList<>()).add(prerequisite[1]);
        }

        int[] visited = new int[numCourses];
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(res, graph, visited, i)) {
                return false;
            }
        }

        return res.size() > 0;
    }

    private boolean dfs(List<Integer> res, Map<Integer, List<Integer>> graph, int[] visited, int course) {
        if (visited[course] == 1) {
            return false;
        }
        if (visited[course] == 2) {
            return true;
        }

        visited[course] = 1;
        if (graph.containsKey(course)) {
            for (int neighbor : graph.get(course)) {
                if (!dfs(res, graph, visited, neighbor)) {
                    return false;
                }
            }
        }
        visited[course] = 2;
        res.add(0, course);

        return true;
    }

    // V1
    // IDEA : BFS
    // https://leetcode.com/problems/course-schedule/solutions/58775/my-java-bfs-solution/
    public boolean canFinish_0_3(int numCourses, int[][] prerequisites) {
        Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
        int[] indegree = new int[numCourses];
        Queue<Integer> queue = new LinkedList<Integer>();
        int count = numCourses;

        /** NOTE !!! numCourses */
        for (int i = 0; i < numCourses; i++) {
            map.put(i, new ArrayList<Integer>());
        }

        /** NOTE !!! prerequisites */
        for (int i = 0; i < prerequisites.length; i++) {
            map.get(prerequisites[i][0]).add(prerequisites[i][1]);

            /** NOTE : below approaches has same effect */
            // V1
            //indegree[prerequisites[i][1]]++;
            // V2
            indegree[prerequisites[i][1]] += 1;
        }

        /** NOTE !!! numCourses */
        for (int i = 0; i < numCourses; i++) {
            // only insert to queue if "indegree == 0" (course has NO prerequisite)
            if (indegree[i] == 0) {
                queue.offer(i); // insert to queue
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll(); // pop from queue
            for (int i : map.get(current)) {

                  /** NOTE : below approaches has same effect */
                  // V1
//                if (--indegree[i] == 0) {
//                    queue.offer(i);
//                }

                // V2
                if (indegree[i] == 1) {
                    queue.offer(i);
                } else {
                    indegree[i] -= 1;
                }

            }
            // when finish a course checking ( course visit and course prerequisite visit)
            count--;
        }
        return count == 0;
    }

    // V2
    // IDEA : BFS
    // https://leetcode.com/problems/course-schedule/solutions/58524/java-dfs-and-bfs-solution/
    public boolean canFinish_2(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        int[] degree = new int[numCourses];
        Queue queue = new LinkedList();
        int count=0;

        for(int i=0;i<numCourses;i++)
            graph[i] = new ArrayList();

        for(int i=0; i<prerequisites.length;i++){
            degree[prerequisites[i][1]]++;
            graph[prerequisites[i][0]].add(prerequisites[i][1]);
        }
        for(int i=0; i<degree.length;i++){
            if(degree[i] == 0){
                queue.add(i);
                count++;
            }
        }

        while(queue.size() != 0){
            int course = (int)queue.poll();
            for(int i=0; i<graph[course].size();i++){
                int pointer = (int)graph[course].get(i);
                degree[pointer]--;
                if(degree[pointer] == 0){
                    queue.add(pointer);
                    count++;
                }
            }
        }
        if(count == numCourses)
            return true;
        else
            return false;
    }

    // V3
    // IDEA :  TOPOLOGICAL SORT
    // https://leetcode.com/problems/course-schedule/solutions/447754/java-topological-sort-dfs-3ms/
    enum Status {
        NOT_VISITED, VISITED, VISITING;
    }

    public boolean canFinish_3(int numCourses, int[][] prerequisites) {
        if(prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) return true;
        // building graph
        List<List<Integer>> list = new ArrayList<>(numCourses);
        for(int i = 0; i < numCourses; i++) {
            list.add(new ArrayList<Integer>());
        }
        for(int[] p: prerequisites) {
            int prerequisite = p[1];
            int course = p[0];
            list.get(course).add(prerequisite);
        }

        Status[] visited = new Status[numCourses];
        for(int i = 0; i < numCourses; i++) {
            // if there is a cycle, return false
            if(dfs(list, visited, i)) return false;
        }
        return true;
    }

    private boolean dfs(List<List<Integer>> list, Status[] visited, int cur) {
        if(visited[cur] == Status.VISITING) return true;
        if(visited[cur] == Status.VISITED) return false;
        visited[cur] = Status.VISITING;
        for(int next: list.get(cur)) {
            if(dfs(list, visited, next)) return true;
        }
        visited[cur] = Status.VISITED;
        return false;
    }

}
