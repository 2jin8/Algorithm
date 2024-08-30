import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			boolean[] visited = new boolean[N];
			long[] x = new long[N];
			long[] y = new long[N];
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

			PriorityQueue<Vertex> pq = new PriorityQueue<>();
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					long dist = (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]);
					graph[i].add(new Vertex(j, dist));
					graph[j].add(new Vertex(i, dist));
				}
			}

			pq.offer(new Vertex(0, 0)); // 0번 노드부터 시작

			// 정점의 수(=N)만큼 반복
			long cost = 0;
			for (int i = 0; i < N; i++) {
				Vertex vertex = null;
				while (true) {
					vertex = pq.poll();
					if (!visited[vertex.idx])
						break;
				}

				long min = vertex.dist;
				int minIdx = vertex.idx;

				// 방문 처리 & 비용 더하기
				cost += min;
				visited[minIdx] = true;

				for (Vertex v : graph[minIdx]) {
					if (!visited[v.idx]) {
						pq.offer(v);
					}
				}
			}
			sb.append("#").append(t).append(" ").append(Math.round(cost * E)).append("\n");
		}
		System.out.println(sb);
	}

	static class Vertex implements Comparable<Vertex> {
		int idx;
		long dist;

		public Vertex(int idx, long dist) {
			this.idx = idx;
			this.dist = dist;
		}

		@Override
		public int compareTo(Vertex o) {
			return Long.compare(this.dist, o.dist); // 거리 기준 오름차순 정렬
		}
	}

}