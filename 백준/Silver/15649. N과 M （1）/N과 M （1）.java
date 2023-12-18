import java.util.*;

public class Main {

    static int n, m;
    static boolean[] visited;
    static int[] result;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 1부터 n까지 m 자리의 수
        n = sc.nextInt();
        m = sc.nextInt();
        visited = new boolean[n + 1];
        result = new int[m];

        dfs(0);
        System.out.println(sb);
    }

    public static void dfs(int cnt) {
        if (cnt == m) { // 탐색 완료된 경우
            for (int i = 0; i < m; i++) {
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) { // 해당 수, 아직 방문하지 않았다면
                visited[i] = true; // 방문 처리
                result[cnt] = i;
                dfs(cnt + 1); // dfs 탐색
                visited[i] = false; // 탐색 후, 방문 처리 취소
            }
        }
    }
}