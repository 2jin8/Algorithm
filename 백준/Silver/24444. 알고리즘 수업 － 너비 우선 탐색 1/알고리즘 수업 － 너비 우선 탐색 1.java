import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, R;
	static int[] orders;
	static ArrayList<Integer>[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			graph[v].add(u);
		}

		// 인접정점은 오름차순으로 방문
		for (int i = 1; i <= N; i++) {
			Collections.sort(graph[i]);
		}

		orders = new int[N + 1];
		bfs(R);
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(orders[i]).append("\n");
		}
		System.out.println(sb);
	}

	static void bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];

		queue.offer(start);
		visited[start] = true;

		int order = 0;
		while (!queue.isEmpty()) {
			int now = queue.poll();
			orders[now] = ++order;

			for (int next : graph[now]) {
				if (!visited[next]) {
					queue.offer(next);
					visited[next] = true;
				}
			}
		}
	}
}
