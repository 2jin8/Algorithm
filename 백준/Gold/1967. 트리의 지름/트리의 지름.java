import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static boolean[] parents;
    static List<Node>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        list = new List[n + 1];
        parents = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            list[parent].add(new Node(child, dist));
            list[child].add(new Node(parent, dist));
        }

        // 루트 1에서 가장 멀리 있는 노드 찾기
        Node node = bfs(1);
        // 가장 멀리 떨어져있는 노드에서 가장 먼 노드까지 길이 찾기
        System.out.println(bfs(node.idx).dist);

    }

    public static Node bfs(int x) {
        Queue<Node> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        visited[x] = true;
        queue.offer(new Node(x, 0));

        int[] total = new int[n + 1];
        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i = 0; i < list[node.idx].size(); i++) {
                Node now = list[node.idx].get(i);
                if (!visited[now.idx]) {
                    queue.offer(now);
                    visited[now.idx] = true;
                    total[now.idx] = total[node.idx] + now.dist;
                }
            }
        }
        int idx = 0, dist = 0;
        for (int i = 1; i <= n; i++) {
            if (dist < total[i]) {
                dist = total[i];
                idx = i;
            }
        }
        return new Node(idx, dist);
    }

    static class Node {
        int idx;
        int dist;

        public Node(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }
    }
}