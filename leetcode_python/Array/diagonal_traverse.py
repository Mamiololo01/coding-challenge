"""

498. Diagonal Traverse
Medium


Add to List

Share
Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.


Example 1:


Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,4,7,5,3,6,8,9]
Example 2:

Input: mat = [[1,2],[3,4]]
Output: [1,2,3,4]
 

Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 104
1 <= m * n <= 104
-105 <= mat[i][j] <= 105


"""

# V0 
# IDEA : while loop + boundary conditions
### NOTE : the "directions" trick
class Solution(object):
    def findDiagonalOrder(self, matrix):
        if not matrix or not matrix[0]: return []
        ### NOTE this trick
        directions = [(-1, 1), (1, -1)]
        count = 0
        res = []
        i, j = 0, 0
        M, N = len(matrix), len(matrix[0])
        while len(res) < M * N:
            if 0 <= i < M and 0 <= j < N:
                res.append(matrix[i][j])
                direct = directions[count % 2]
                i, j = i + direct[0], j + direct[1]
                continue
            elif i < 0 and 0 <= j < N:
                i += 1
            elif 0 <= i < M and j < 0:
                j += 1
            elif i < M and j >= N:
                i += 2
                j -= 1
            elif i >= M and j < N:
                j += 2
                i -= 1
            count += 1
        return res

# V1
# https://blog.csdn.net/fuxuemingzhu/article/details/82528226
class Solution(object):
    def findDiagonalOrder(self, matrix):
        """
        :type matrix: List[List[int]]
        :rtype: List[int]
        """
        if not matrix or not matrix[0]: return []
        directions = [(-1, 1), (1, -1)]
        count = 0
        res = []
        i, j = 0, 0
        M, N = len(matrix), len(matrix[0])
        while len(res) < M * N:
            if 0 <= i < M and 0 <= j < N:
                res.append(matrix[i][j])
                direct = directions[count % 2]
                i, j = i + direct[0], j + direct[1]
                continue
            elif i < 0 and 0 <= j < N:
                i += 1
            elif 0 <= i < M and j < 0:
                j += 1
            elif i < M and j >= N:
                i += 2
                j -= 1
            elif i >= M and j < N:
                j += 2
                i -= 1
            count += 1
        return res

# V1'
# http://bookshadow.com/weblog/2017/02/05/leetcode-diagonal-traverse/
class Solution(object):
    def findDiagonalOrder(self, matrix):
        """
        :type matrix: List[List[int]]
        :rtype: List[int]
        """
        if not matrix: return []
        i, j, k = 0, 0, 1
        w, h = len(matrix), len(matrix[0])
        ans = []
        for x in range(w * h):
            ans.append(matrix[i][j])
            if k > 0:
                di, dj = i - 1, j + 1
            else:
                di, dj = i + 1, j - 1
            if 0 <= di < w and 0 <= dj < h:
                i, j = di, dj
            else:
                if k > 0:
                    if j + 1 < h:
                        j += 1
                    else:
                        i += 1
                else:
                    if i + 1 < w:
                        i += 1
                    else:
                        j += 1
                k *= -1
        return ans

# V2
