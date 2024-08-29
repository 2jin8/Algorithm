import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static double E;
	static long[] x, y;
	static int[] parents;
	static PriorityQueue<Edge> pq;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			x = new long[N]; // 각 섬의 x좌표
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				x[i] = Long.parseLong(st.nextToken());
			}

			y = new long[N]; // 각 섬의 y좌표
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				y[i] = Long.parseLong(st.nextToken());
			}

			E = Double.parseDouble(br.readLine()); // 환경 부담 세율

			pq = new PriorityQueue<>();
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					// 연산에서 오버플로우 날 수 있으므로 x, y 배열 long 타입으로 선언하기..
					long L = (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]);
					pq.offer(new Edge(i, j, L));
				}
			}

			int cnt = 0; // 연결된 간선의 수
			long pay = 0L;
			make();
			while (!pq.isEmpty()) {
				Edge edge = pq.poll();
				if (union(edge.island1, edge.island2)) {
					pay += edge.pay;
					if (++cnt == N - 1) {
						break;
					}
				}
			}
			sb.append("#").append(t).append(" ").append(Math.round(pay * E)).append("\n");
		}
		System.out.println(sb);
	}

	static void make() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
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
		if (aRoot == bRoot)
			return false;

		parents[bRoot] = aRoot;
		return true;
	}

	static class Edge implements Comparable<Edge> {
		int island1, island2;
		long pay;

		public Edge(int island1, int island2, long pay) {
			this.island1 = island1;
			this.island2 = island2;
			this.pay = pay;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.pay, o.pay);
		}
	}
}