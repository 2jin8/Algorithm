import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	static final int MAX = Integer.MAX_VALUE;
	static int V, E, minEdge[];
	static boolean[] visited;
	static ArrayList<Vertex>[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			// 초기화
			visited = new boolean[V + 1]; // 방문 확인용
			minEdge = new int[V + 1]; // 최소 가중치 저장
			Arrays.fill(minEdge, MAX);

			graph = new ArrayList[V + 1]; // 정점 연결 정보
			for (int i = 1; i <= V; i++) {
				graph[i] = new ArrayList<>();
			}

			PriorityQueue<Vertex> pq = new PriorityQueue<>(new Comparator<Vertex>() {
				@Override
				public int compare(Vertex o1, Vertex o2) {
					return Integer.compare(o1.weight, o2.weight);
				}
			});

			// 정점의 정보 저장 (양방향)
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				graph[a].add(new Vertex(b, c));
				graph[b].add(new Vertex(a, c));
			}

			// 1번 정점에서 시작
			minEdge[1] = 0;
			pq.offer(new Vertex(1, 0));

			int cnt = 0;
			long cost = 0; // 최소 스패닝 트리의 가중치
			while (!pq.isEmpty()) {
				Vertex vertex = pq.poll();
				int min = vertex.weight, minIdx = vertex.idx;
				if (visited[minIdx])
					continue;

				visited[minIdx] = true;
				cost += min;

				if (cnt == V)
					break;

				for (Vertex v : graph[minIdx]) {
					if (!visited[v.idx] && minEdge[v.idx] > v.weight) {
						pq.offer(v);
						minEdge[v.idx] = v.weight;
					}
				}
			}
			sb.append("#").append(t).append(" ").append(cost).append("\n");
		}
		System.out.println(sb);
	}

	static class Vertex {
		int idx, weight;

		public Vertex(int idx, int weight) {
			this.idx = idx;
			this.weight = weight;
		}
	}
}