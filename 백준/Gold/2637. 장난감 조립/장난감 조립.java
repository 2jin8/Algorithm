import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] inDegree;
    static boolean[] isBasic;
    static int[][] needs, dp;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        inDegree = new int[N + 1];
        isBasic = new boolean[N + 1];
        needs = new int[N + 1][N + 1];
        dp = new int[N + 1][N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            dp[i][i] = 1; // 자기 자신은 본인의 부품 필요로 함
        }

        StringTokenizer st = null;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            // x를 만드는데 부품 y가 k개 필요하다
            inDegree[x]++;
            graph[y].add(x);
            needs[x][y] = k;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
                isBasic[i] = true;
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();

            // 연결된 간선 끊기
            for (int next : graph[now]) {
                // 필요한 부품 정보 더하기
                for (int i = 1; i <= N; i++) {
                    dp[next][i] += dp[now][i] * needs[next][now];
                }

                // 연결된 부품의 진입 차수가 0이 되는 경우 큐에 넣기
                if (--inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (isBasic[i]) { // 기본 부품인 경우만 출력
                sb.append(i).append(" ").append(dp[N][i]).append("\n");
            }
        }
        System.out.println(sb);
    }
}
