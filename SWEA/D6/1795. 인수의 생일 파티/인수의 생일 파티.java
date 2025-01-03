import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	static int N, M, X;
	static ArrayList<Node>[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			graph = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<Node>();
			}

			for (int i = 0; i < M; i++) {
				// x에서 y로 가는데 c가 걸리는 단방향 도로 존재
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				graph[x].add(new Node(y, c));
			}

			int ans = 0;
			for (int i = 1; i <= N; i++) {
				if (i == X)
					continue;

				// i -> X
				int time = dijkstra(i, X);

				// X -> i
				time += dijkstra(X, i);

				ans = Math.max(ans, time);
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static int dijkstra(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.d, n2.d));
		int[] minDist = new int[N + 1];
		Arrays.fill(minDist, Integer.MAX_VALUE);
		pq.offer(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node now = pq.poll();
			if (now.d > minDist[now.x])
				continue;

			minDist[now.x] = now.d;
			if (now.x == end)
				break;

			for (Node next : graph[now.x]) {
				if (minDist[next.x] > now.d + next.d) {
					minDist[next.x] = now.d + next.d;
					pq.offer(new Node(next.x, minDist[next.x]));
				}
			}
		}
		return minDist[end];
	}

	static class Node {
		int x, d;

		public Node(int x, int d) {
			this.x = x;
			this.d = d;
		}
	}
}