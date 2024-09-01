import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int V, E;
	static int[] parents;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		Edge[] edges = new Edge[E];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(a, b, c);
		}

		// 가중치 기준 오름차순 정렬
		Arrays.sort(edges);
		int weight = 0;
		make();
		for (Edge edge : edges) {
			// 사이클이 발생하지 않으면 가중치 더하기
			if (union(edge.a, edge.b)) {
				weight += edge.w;
			}
		}
		System.out.println(weight);
	}

	static void make() {
		parents = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			parents[i] = i;
		}
	}

	static int findSet(int a) {
		if (a == parents[a])
			return a;

		return parents[a] = findSet(parents[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) // 대표자가 같은 경우
			return false;

		parents[bRoot] = aRoot;
		return true;
	}

	static class Edge implements Comparable<Edge> {
		int a, b, w;

		public Edge(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w; // 가중치 기준 오름차순 정렬
		}
	}
}