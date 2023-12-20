import java.util.*;
import java.io.*;

public class Main {
    private static int n, m;
    private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    private static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 사람의 수
        m = Integer.parseInt(st.nextToken()); // 친구 관계의 수
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 양방향 관계
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for (int i = 0; i < n; i++) {
            visited = new boolean[n];
            dfs(i, 0);
        }
        System.out.println(0);
    }

    private static void dfs(int v, int depth) {
        if (depth >= 5) {  // 깊이 5보다 크면 탐색할 필요 없음 → 바로 종료
            System.out.println(1);
            System.exit(0);
        }

        for (int i = 0; i < graph.get(v).size(); i++) {
            int x = graph.get(v).get(i);
            if (!visited[x]) { // 아직 방문하지 않았으면
                visited[x] = true; // 방문 처리 하기
                dfs(x, depth + 1); // 탐색 시작
                visited[x] = false; // 탐색 종료 후, 방문 처리 취소
            }
        }
    }
}