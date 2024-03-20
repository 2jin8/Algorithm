import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Integer>[] lists;
    static int[] root;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        lists = new List[N + 1];
        root = new int[N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            lists[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lists[a].add(b);
            lists[b].add(a);
        }
        bfs(1); // 루트 노드인 1에서 탐색 시작
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            sb.append(root[i]).append("\n");
        }
        System.out.println(sb);
    }

    public static void bfs(int x) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x);
        visited[x] = true;

        while (!queue.isEmpty()) {
            x = queue.poll();

            for (int i = 0; i < lists[x].size(); i++) {
                int y = lists[x].get(i);
                if (!visited[y]) {
                    root[y] = x; // 부모 기록하기
                    queue.offer(y);
                    visited[y] = true;
                }
            }
        }
    }
}