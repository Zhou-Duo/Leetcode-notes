// 1. BFS
class Solution1 {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        visited.add(0);
        queue.add(0);
        while (!queue.isEmpty()) {
            int currRoom = queue.poll();
            List<Integer> keys = rooms.get(currRoom);
            for (int key : keys) {
                if (visited.add(key)) {
                    queue.add(key);
                }
            }
        }
        return (visited.size() == rooms.size());
    }
}

// 2. DFS
class Solution {
    private boolean[] visited;
    private int num;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        visited = new boolean[rooms.size()];
        num = 0;
        dfs(rooms, 0);
        return num == rooms.size();
    }

    public void dfs(List<List<Integer>> rooms, int x) {
        if (!visited[x]) {
            visited[x] = true;
            num += 1;
            for (int key : rooms.get(x)) {
                dfs(rooms, key);
            }
        }
    }
}