import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static long[] x, y, minEdge;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			visited = new boolean[N];
			x = new long[N];
			y = new long[N];
			minEdge = new long[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				x[i] = Long.parseLong(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				y[i] = Long.parseLong(st.nextToken());
			}

			double E = Double.parseDouble(br.readLine());

			ArrayList<Vertex>[] graph = new ArrayList[N];
			for (int i = 0; i < N; i++) {
				graph[i] = new ArrayList<Vertex>();
			}
			
			Arrays.fill(minEdge, Long.MAX_VALUE);

			PriorityQueue<Vertex> pq = new PriorityQueue<>((v1, v2) -> Long.compare(v1.dist, v2.dist));
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					long dist = (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]);
					graph[i].add(new Vertex(j, dist));
					graph[j].add(new Vertex(i, dist));
				}
			}

			pq.offer(new Vertex(0, 0)); // 0번 노드부터 시작
			minEdge[0] = 0;

			int cnt = 0;
			long dist = 0;
			while (!pq.isEmpty()) {
				Vertex vertex = pq.poll();
				int minIdx = vertex.idx;
				long min = vertex.dist;

				if (visited[minIdx])
					continue;

				visited[minIdx] = true;
				dist += min;
				if (++cnt == N) // MST 완성
					break;

				for (Vertex v : graph[minIdx]) {
					if (!visited[v.idx] && minEdge[v.idx] > v.dist) {
						minEdge[v.idx] = v.dist;
						pq.offer(v);
					}
				}

			}
			sb.append("#").append(t).append(" ").append(Math.round(dist * E)).append("\n");
		}
		System.out.println(sb);
	}

	static class Vertex {
		int idx;
		long dist;

		public Vertex(int idx, long dist) {
			this.idx = idx;
			this.dist = dist;
		}
	}
}