import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static boolean[] visited;
    static ArrayList<Integer>[] computers;
    public static void main(String[] args) throws IOException {
        // 1번 컴퓨터를 통해 바이러스에 걸리는 컴퓨터의 수 = 1번 컴퓨터와 연결된 컴퓨터의 수
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int T = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];
        computers = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            computers[i] = new ArrayList<>();
        }

        // 연결 정보 기록하기
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int c1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            computers[c1].add(c2);
            computers[c2].add(c1);
        }
        System.out.println(bfs(1));
    }

    static int bfs(int x) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x);
        visited[x] = true;

        int total = 0; // 바이러스에 걸리는 컴퓨터의 수
        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int i = 0; i < computers[now].size(); i++) {
                int next = computers[now].get(i);
                if (!visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                    total++;
                }
            }
        }
        return total;
    }
}