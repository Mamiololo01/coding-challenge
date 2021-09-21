-- 1112.Highest Grade For Each Student
-- Table: Enrollments

-- +---------------+---------+
-- | Column Name   | Type    |
-- +---------------+---------+
-- | student_id    | int     |
-- | course_id     | int     |
-- | grade         | int     |
-- +---------------+---------+
-- (student_id, course_id) is the primary key of this table.

-- Write a SQL query to find the highest grade with its corresponding course for each student. In case of a tie, you should find the course with the smallest course_id. The output must be sorted by increasing student_id.

-- The query result format is in the following example:

-- Enrollments table:
-- +------------+-------------------+
-- | student_id | course_id | grade |
-- +------------+-----------+-------+
-- | 2          | 2         | 95    |
-- | 2          | 3         | 95    |
-- | 1          | 1         | 90    |
-- | 1          | 2         | 99    |
-- | 3          | 1         | 80    |
-- | 3          | 2         | 75    |
-- | 3          | 3         | 82    |
-- +------------+-----------+-------+

-- Result table:
-- +------------+-------------------+
-- | student_id | course_id | grade |
-- +------------+-----------+-------+
-- | 1          | 2         | 99    |
-- | 2          | 2         | 95    |
-- | 3          | 3         | 82    |
-- +------------+-----------+-------+

# V0
select student_id, min(course_id) as course_id, grade
from Enrollments
where (student_id, grade) in 
    (select student_id, max(grade)
    from Enrollments
    group by student_id)
group by student_id, grade
order by student_id asc

# V1
# https://code.dennyzhang.com/highest-grade-for-each-student
select student_id, min(course_id) as course_id, grade
from Enrollments
where (student_id, grade) in 
    (select student_id, max(grade)
    from Enrollments
    group by student_id)
group by student_id
order by student_id asc

# V2
# Time:  O(nlogn)
# Space: O(n)
SELECT student_id, 
       Min(course_id) AS course_id, 
       grade 
FROM   enrollments 
WHERE  ( student_id, grade ) IN (SELECT student_id, 
                                        Max(grade) 
                                 FROM   enrollments 
                                 GROUP  BY student_id 
                                 ORDER  BY NULL) 
GROUP  BY student_id 
ORDER  BY student_id 