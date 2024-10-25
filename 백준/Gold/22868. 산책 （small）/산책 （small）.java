import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, S, E;
	static boolean[] visited;
	static int[] dist, record;
	static ArrayList<Integer>[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new boolean[N + 1];
		dist = new int[N + 1];
		record = new int[N + 1];
		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}

		// 사전 순으로 방문해야 하므로 정렬하기
		for (int i = 1; i <= N; i++) {
			Collections.sort(graph[i]);
		}

		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		int totalDist = bfs(S, E);
		check(); // 최단 경로로 오기 위해 선택된 정점 기록하기
		Arrays.fill(dist, 0); // 거리 배열 초기화
		totalDist += bfs(E, S);
		System.out.println(totalDist);
	}
	
	static void check() {
		Arrays.fill(visited, false);
		
		int now = record[E];
		while (true) {
			if (now == record[now]) break;
			
			visited[now] = true;
			now = record[now];
		}
	}

	static int bfs(int start, int end) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(start);
		visited[start] = true;
		record[start] = start;

		while (!queue.isEmpty()) {
			int now = queue.poll();
			if (now == end)
				break;

			for (int next : graph[now]) {
				if (visited[next])
					continue;

				visited[next] = true;
				record[next] = now;
				dist[next] = dist[now] + 1;
				queue.offer(next);
			}
		}
		return dist[end];
	}
}