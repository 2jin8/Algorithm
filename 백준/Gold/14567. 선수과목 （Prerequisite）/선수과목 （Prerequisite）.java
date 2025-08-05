import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] inDegree, time;
    static ArrayList<Integer>[] graph;
    // 최소 몇 학기에 이수할 수 있는지 == 내가 진입차수가 0이 되는게 언제인지
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 진입차수 세기
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        time = new int[N + 1];
        inDegree = new int[N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            inDegree[b]++;
        }

        // 진입차수가 0인 과목 큐에 넣기
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
                time[i] = 1;
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int next : graph[now]) {
                // 현재 과목과 연결된 간선 끊고, 진입차수가 0이 된다면 큐에 넣기
                if (--inDegree[next] == 0) {
                    queue.offer(next);
                    time[next] = time[now] + 1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(time[i]).append(" ");
        }
        System.out.println(sb);
    }
}
