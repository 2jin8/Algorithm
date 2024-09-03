import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static final int INF = Integer.MAX_VALUE;
	static int V, E, K; // K: 시작 정점
	static int[] minDist;
	static boolean[] visited;
	static ArrayList<Node>[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		minDist = new int[V + 1];
		visited = new boolean[V + 1];
		graph = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}

		// 연결 정보
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[u].add(new Node(v, w));
		}
		getMinDist();

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			sb.append(minDist[i] == INF ? "INF" : minDist[i]).append("\n");
		}
		System.out.println(sb);
	}

	static void getMinDist() {
		Arrays.fill(minDist, INF);
		PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.w, n2.w));

		// 시작 정점
		minDist[K] = 0;
		pq.offer(new Node(K, minDist[K]));

		while (!pq.isEmpty()) {
			Node now = pq.poll();

			// 이미 방문한 정점
			if (visited[now.v])
				continue;
			// 방문 처리
			visited[now.v] = true;

			for (Node n : graph[now.v]) {
				if (visited[n.v])
					continue;

				if (minDist[n.v] > now.w + n.w) {
					minDist[n.v] = now.w + n.w;
					pq.offer(new Node(n.v, minDist[n.v]));
				}
			}
		}
	}

	static class Node {
		int v, w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
}