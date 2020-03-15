/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have to
 * first take course 1, which is expressed as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, is it
 * possible for you to finish all courses?
 * 
 * 
 * This graph can be formulated as directed acyclic graph and hence the idea is
 * to use topological ordering. This reduces to finding a topological ordering,
 * if one exists, it implies the schedule can be done, otherwise, it is not
 * possible to complete it.
 * 
 * V is numCourse, E is the edges 
 * Space Complexity: O(V+E) 
 * Time Complexity: O(V+E)
 * 
 * 
 */
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // base case checks
        if (numCourses < 2) {
            return true;
        }
        int[] indegree = new int[numCourses];
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        // init empty graph
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new LinkedList<>());
        }
        // create graph and compute indegree array
        for (int[] tuple : prerequisites) {
            int course = tuple[0];
            int prerequisite = tuple[1];
            List<Integer> courses = graph.get(prerequisite);
            courses.add(course);
            indegree[course]++;
        }
        // create queue of courses with indegrees 0, the ones that have no prereqs.
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        // if no such starting point exists.
        if (queue.size() == 0) {
            return false;
        }
        // we want to generate a topological ordering
        int count = numCourses;
        while (queue.size() > 0) {
            int currentCourse = queue.remove();
            count--;
            List<Integer> correspondingCourses = graph.get(currentCourse);
            for (int course : correspondingCourses) {
                // we are taking course, so reduce indegree of edge course to corresponding course
                indegree[course]--;
                if (indegree[course] == 0) {
                    queue.add(course);
                }
            }
        }
        // if a topo order consisting of all nodes has been generated, count should be
        // 0.
        return count == 0;
    }
}