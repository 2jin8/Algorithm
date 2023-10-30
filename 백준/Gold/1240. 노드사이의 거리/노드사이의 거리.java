import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[][] trees;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        trees = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) trees[i][j] = 0;
                else trees[i][j] = INF;
            }
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            trees[u][v] = d;
            trees[v][u] = d;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            bw.write(bfs(start, end) + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static int bfs(int start, int end) {
        boolean[] visited = new boolean[n + 1];
        visited[start] = true;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int i = node.point;
            int v = node.value;
            if (i == end) {
                return v;
            }

            for (int j = 1; j <= n; j++) {
                if (trees[i][j] == 0 || trees[i][j] == INF)
                    continue;

                if (!visited[j]) {
                    visited[j] = true;
                    queue.offer(new Node(j, v + trees[i][j]));
                }
            }
        }
        return -1;
    }
}

class Node {
    int point;
    int value;

    public Node(int point, int value) {
        this.point = point;
        this.value = value;
    }
}