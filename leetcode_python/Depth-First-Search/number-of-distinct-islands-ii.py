"""

LeetCode 711 - Number of Distinct Islands II

https://protegejj.gitbooks.io/algorithm-practice/content/711-number-of-distinct-islands-ii.html
Given a non-empty 2D arraygridof 0's and 1's, an island is a group of1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another if they have the same shape, or have the same shape after rotation (90, 180, or 270 degrees only) or reflection (left/right direction or up/down direction).
Example 1:
11000
10000
00001
00011
Given the above grid map, return1.
Notice that:
11
1
and
 1
11
are considered same island shapes. Because if we make a 180 degrees clockwise rotation on the first island, then two islands will have the same shapes.
Example 2:
11100
10001
01001
01110
Given the above grid map, return2.
Here are the two distinct islands:
111
1
and
1
1


Notice that:
111
1
and
1
111
are considered same island shapes. Because if we flip the first array in the up/down direction, then they have the same shapes.
Note:The length of each dimension in the givengriddoes not exceed 50.

"""

# V0
# IDEA : DFS + normalize
class Solution(object):
    def numDistinctIslands2(self, grid):
        directions = [(0, -1), (0, 1), (-1, 0), (1, 0)]

        def dfs(i, j, grid, island):
            if not (0 <= i < len(grid) and \
                    0 <= j < len(grid[0]) and \
                    grid[i][j] > 0):
                return False
            grid[i][j] *= -1
            island.append((i, j))
            for d in directions:
                dfs(i+d[0], j+d[1], grid, island)
            return True

        ### NOTE : we use this normalize func to normalize the island and only consider the
        #          unique islands as expected result
        def normalize(island):
            shapes = [[] for _ in range(8)]
            for x, y in island:
                rotations_and_reflections = [[ x,  y], [ x, -y], [-x, y], [-x, -y],
                                             [ y,  x], [ y, -x], [-y, x], [-y, -x]]
                for i in range(len(rotations_and_reflections)):
                    shapes[i].append(rotations_and_reflections[i])
            # https://blog.csdn.net/magicbean2/article/details/79282937
            # we go through 8 "possible" transformed islands (via rotation/mirror)
            # then return the 1st island as representative
            for shape in shapes:
                shape.sort()  # Time: O(ilogi), i is the size of the island, the max would be (m * n)
                origin = list(shape[0])
                for p in shape:
                    p[0] -= origin[0]
                    p[1] -= origin[1]
            # then return the 1st island as representative
            return min(shapes)

        ### NOTE we use set() here to avoid duplication
        islands = set()
        for i in range(len(grid)):
            for j in range(len(grid[0])):
                island = []
                if dfs(i, j, grid, island):
                    islands.add(str(normalize(island)))
        # return the "unique" island count
        return len(islands)

# V1
# IDEA : DFS + normalize
# https://github.com/kamyu104/LeetCode-Solutions/blob/master/Python/number-of-distinct-islands-ii.py
# Time:  O((m * n) * log(m * n))
# Space: O(m * n)
class Solution(object):
    def numDistinctIslands2(self, grid):
        directions = [(0, -1), (0, 1), (-1, 0), (1, 0)]

        def dfs(i, j, grid, island):
            if not (0 <= i < len(grid) and \
                    0 <= j < len(grid[0]) and \
                    grid[i][j] > 0):
                return False
            grid[i][j] *= -1
            island.append((i, j))
            for d in directions:
                dfs(i+d[0], j+d[1], grid, island)
            return True

        ### NOTE : we use this normalize func to normalize the island and only consider the
        #          unique islands as expected result
        def normalize(island):
            shapes = [[] for _ in range(8)]
            for x, y in island:
                rotations_and_reflections = [[ x,  y], [ x, -y], [-x, y], [-x, -y],
                                             [ y,  x], [ y, -x], [-y, x], [-y, -x]]
                for i in range(len(rotations_and_reflections)):
                    shapes[i].append(rotations_and_reflections[i])
            # https://blog.csdn.net/magicbean2/article/details/79282937
            # we go through 8 "possible" transformed islands (via rotation/mirror)
            # then return the 1st island as representative
            for shape in shapes:
                shape.sort()  # Time: O(ilogi), i is the size of the island, the max would be (m * n)
                origin = list(shape[0])
                for p in shape:
                    p[0] -= origin[0]
                    p[1] -= origin[1]
            # then return the 1st island as representative
            return min(shapes)

        ### NOTE we use set() here to avoid duplication
        islands = set()
        for i in range(len(grid)):
            for j in range(len(grid[0])):
                island = []
                if dfs(i, j, grid, island):
                    islands.add(str(normalize(island)))
        # return the "unique" island count
        return len(islands)

# V1 
# https://www.cnblogs.com/grandyang/p/8542820.html
# IDEA : C++
# class Solution {
# public:
#     int numDistinctIslands2(vector<vector<int>>& grid) {
#         int m = grid.size(), n = grid[0].size();
#         set<vector<pair<int, int>>> st;
#         for (int i = 0; i < m; ++i) {
#             for (int j = 0; j < n; ++j) {
#                 if (grid[i][j]) {
#                     vector<pair<int, int>> shape;
#                     helper(grid, i, j, shape);
#                     st.insert(normalize(shape));
#                 }
#             }
#         }
#         return st.size();
#     }
#     void helper(vector<vector<int>>& grid, int x, int y, vector<pair<int, int>>& shape) {
#         if (x < 0 || x >= grid.size() || y < 0 || y >= grid[0].size()) return;
#         if (grid[x][y] == 0) return;
#         grid[x][y] = 0;
#         shape.push_back({x, y});
#         helper(grid, x + 1, y, shape);
#         helper(grid, x - 1, y, shape);
#         helper(grid, x, y + 1, shape);
#         helper(grid, x, y - 1, shape);
#     }
#     vector<pair<int, int>> normalize(vector<pair<int, int>>& shape) {
#         vector<vector<pair<int, int>>> shapes(8);
#         for (auto &a : shape) {
#             int x = a.first, y = a.second;
#             shapes[0].push_back({x, y});
#             shapes[1].push_back({x, -y});
#             shapes[2].push_back({-x, y});
#             shapes[3].push_back({-x, -y});
#             shapes[4].push_back({y, x});
#             shapes[5].push_back({y, -x});
#             shapes[6].push_back({-y, x});
#             shapes[7].push_back({-y, -x});
#         }
#         for (auto &a : shapes) {
#             sort(a.begin(), a.end());
#             for (int i = (int)shape.size() - 1; i >= 0; --i) {
#                 a[i].first -= a[0].first;
#                 a[i].second -= a[0].second;
#             }
#         }
#         sort(shapes.begin(), shapes.end());
#         return shapes[0];
#     }
# };

# V1'
# https://www.cnblogs.com/grandyang/p/8542820.html
# IDEA : C++
# class Solution {
# public:
#     int numDistinctIslands2(vector<vector<int>>& grid) {
#         int m = grid.size(), n = grid[0].size();
#         set<vector<pair<int, int>>> st;
#         for (int i = 0; i < m; ++i) {
#             for (int j = 0; j < n; ++j) {
#                 if (grid[i][j] == 0) continue;
#                 grid[i][j] = 0;
#                 vector<pair<int, int>> shape{{i, j}};
#                 int i = 0;
#                 while (i < shape.size()) {
#                     auto t = shape[i++];
#                     int x = t.first, y = t.second;
#                     if (x > 0 && grid[x - 1][y] != 0) {
#                         grid[x - 1][y] = 0;
#                         shape.push_back({x - 1, y});
#                     }
#                     if (x + 1 < m && grid[x + 1][y] != 0) {
#                         grid[x + 1][y] = 0;
#                         shape.push_back({x + 1, y});
#                     }
#                     if (y > 0 && grid[x][y - 1] != 0) {
#                         grid[x][y - 1] = 0;
#                         shape.push_back({x, y - 1});
#                     }
#                     if (y + 1 < n && grid[x][y + 1] != 0) {
#                         grid[x][y + 1] = 0;
#                         shape.push_back({x, y + 1});
#                     }
#                 }
#                 st.insert(normalize(shape));
#             }
#         }
#         return st.size();
#     }
#     vector<pair<int, int>> normalize(vector<pair<int, int>>& shape) {
#         vector<vector<pair<int, int>>> shapes(8);
#         for (auto &a : shape) {
#             int x = a.first, y = a.second;
#             shapes[0].push_back({x, y});
#             shapes[1].push_back({x, -y});
#             shapes[2].push_back({-x, y});
#             shapes[3].push_back({-x, -y});
#             shapes[4].push_back({y, x});
#             shapes[5].push_back({y, -x});
#             shapes[6].push_back({-y, x});
#             shapes[7].push_back({-y, -x});
#         }
#         for (auto &a : shapes) {
#             sort(a.begin(), a.end());
#             for (int i = (int)shape.size() - 1; i >= 0; --i) {
#                 a[i].first -= a[0].first;
#                 a[i].second -= a[0].second;
#             }
#         }
#         sort(shapes.begin(), shapes.end());
#         return shapes[0];
#     }
# };

# V2