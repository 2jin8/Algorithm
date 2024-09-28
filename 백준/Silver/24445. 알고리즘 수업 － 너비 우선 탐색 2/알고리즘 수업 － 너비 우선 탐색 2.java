import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, R;
	static int[] visitNum;
	static boolean[] visited;
	static ArrayList<Integer>[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		visitNum = new int[N + 1];
		visited = new boolean[N + 1];
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

		for (int i = 1; i <= N; i++) {
			Collections.sort(graph[i], Collections.reverseOrder());
//			Collections.sort(graph[i], (i1, i2) -> Integer.compare(i2, i1)); // 내림차순 정렬
		}

		bfs(R);
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(visitNum[i]).append("\n");
		}
		System.out.println(sb);
	}

	static void bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(start);
		visited[start] = true;

		int num = 0;
		while (!queue.isEmpty()) {
			int now = queue.poll();
			visitNum[now] = ++num;

			for (int next : graph[now]) {
				if (!visited[next]) {
					queue.offer(next);
					visited[next] = true;
				}
			}
		}
	}
}
