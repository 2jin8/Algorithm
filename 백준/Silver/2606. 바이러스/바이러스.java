import java.io.*;
import java.util.*;

public class Main {
    static int N, total;
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
        dfs(1);
        System.out.println(total);
    }

    static void dfs(int x) {
        visited[x] = true;

        for (int i = 0; i < computers[x].size(); i++) {
            int next = computers[x].get(i);
            if (!visited[next]) {
                total++;
                dfs(next);
            }
        }
    }
}