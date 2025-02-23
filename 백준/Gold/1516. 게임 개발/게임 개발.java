import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] inDegree, times, ans; // inDegree: 진입 차수, times: 건물 짓는데 걸리는 시간
	static ArrayList<Integer>[] graph, reverse;
	static Queue<Integer> queue = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		inDegree = new int[N + 1];
		times = new int[N + 1];
		graph = new ArrayList[N + 1];
		reverse = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
			reverse[i] = new ArrayList<>();
		}

		StringTokenizer st = null;
		ans = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			times[i] = Integer.parseInt(st.nextToken());
			int prev = Integer.parseInt(st.nextToken());
			while (prev != -1) {
				graph[prev].add(i);
				reverse[i].add(prev);
				inDegree[i]++;
				prev = Integer.parseInt(st.nextToken());
			}

			// 진입 차수가 0인 것 큐에 넣기
			if (inDegree[i] == 0) {
				queue.offer(i);
				ans[i] = times[i];
			}
		}

		while (!queue.isEmpty()) {
			int now = queue.poll();

			for (int next : graph[now]) {
				// 연결 끊기 & 진입 차수가 0이 되면 큐에 넣기
				if (--inDegree[next] == 0) {
					queue.offer(next);

					// 현재 건물을 짓기 전, 가장 오래걸리는 시간 찾기
					int maxTime = 0;
					for (int prev : reverse[next]) {
						maxTime = Math.max(maxTime, ans[prev]);
					}

					ans[next] = maxTime + times[next];
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(ans[i]).append("\n");
		}
		System.out.println(sb);
	}
}