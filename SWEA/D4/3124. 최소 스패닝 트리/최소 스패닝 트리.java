import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int V, E; // V: 정점의 개수, E: 간선의 개수
	static int[] parents;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			make();
			
			Edge[] edges = new Edge[E];
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				edges[i] = new Edge(a, b, c);
			}
			// 가중치를 기준으로 오름차순 정렬
			Arrays.sort(edges);

			long totalWeight = 0L;
			for (Edge edge : edges) {
				// 정점 x, y가 다른 집합에 포함된 경우
				if (union(edge.x, edge.y)) {
					totalWeight += edge.weight;
				}
			}
			sb.append("#").append(t).append(" ").append(totalWeight).append("\n");
		}
		System.out.println(sb);
	}

	static void make() {
		parents = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			parents[i] = -1;
		}
	}

	static int findSet(int a) {
		// 대표자 찾기(parents[a] >= 0: 대표자 번호, parents[a] < 0: 그룹 개수(=대표자를 뜻함))
		if (parents[a] < 0) // 대표자를 찾은 경우
			return a;

		return parents[a] = findSet(parents[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot)
			return false;

		parents[aRoot] += parents[bRoot]; // b의 자식 수를 a에 더하기
		parents[bRoot] = aRoot; // 새로운 대표자 정보 기록
		return true;
	}

	static class Edge implements Comparable<Edge> {
		int x, y, weight;

		public Edge(int x, int y, int weight) {
			this.x = x;
			this.y = y;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
}