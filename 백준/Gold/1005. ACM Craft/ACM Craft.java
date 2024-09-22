import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static int[] inDegree, times, dp;
	static ArrayList<Integer>[] graph, rGraph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			dp = new int[N + 1];
			times = new int[N + 1];
			inDegree = new int[N + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				times[i] = Integer.parseInt(st.nextToken());
			}

			graph = new ArrayList[N + 1];
			rGraph = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<>();
				rGraph[i] = new ArrayList<>();
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				// 건설 순서: a -> b
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				inDegree[b]++; // 진입차수 증가
				graph[a].add(b); // 건설 순서 저장
				rGraph[b].add(a); // 역 건설 순서 저장
			}

			int target = Integer.parseInt(br.readLine());

			Queue<Integer> queue = new ArrayDeque<>();
			for (int i = 1; i <= N; i++) {
				// 진입 차수가 0인 건물 큐에 넣기
				if (inDegree[i] == 0) {
					queue.offer(i);
				}
			}

			while (!queue.isEmpty()) {
				int now = queue.poll();
				for (int prev : rGraph[now]) {
					dp[now] = Math.max(dp[now], dp[prev]);
				}
				dp[now] += times[now];
				if (now == target)
					break;

				for (int next : graph[now]) {
					if (--inDegree[next] == 0) {
						queue.offer(next);
					}
				}
			}
			sb.append(dp[target]).append("\n");
		}
		System.out.println(sb);
	}
}