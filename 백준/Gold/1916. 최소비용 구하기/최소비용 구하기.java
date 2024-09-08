import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, MAX = 1_000_000_001;
	static int[] costs; // 버스 비용
	static boolean[] visited;
	static ArrayList<Point>[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 도시의 개수
		M = Integer.parseInt(br.readLine()); // 버스의 개수

		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<Point>();
		}

		StringTokenizer st = null;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[a].add(new Point(b, c));
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		System.out.println(getMinCost(start, end));
	}

	static int getMinCost(int start, int end) {
		visited = new boolean[N + 1];
		costs = new int[N + 1];
		Arrays.fill(costs, MAX); // 최댓값으로 초기화

		PriorityQueue<Point> pq = new PriorityQueue<>();
		costs[start] = 0; // 시작 위치 초기화
		pq.offer(new Point(start, 0));

		while (!pq.isEmpty()) {
			Point now = pq.poll();
			if (now.x == end) {
				return now.cost;
			}

			// 이미 방문한 곳이면 넘어가기
			if (visited[now.x])
				continue;

			visited[now.x] = true;

			for (Point p : graph[now.x]) {
				if (costs[p.x] > costs[now.x] + p.cost) {
					costs[p.x] = costs[now.x] + p.cost;
					pq.offer(new Point(p.x, costs[p.x]));
				}
			}
		}
		return -1;
	}

	static class Point implements Comparable<Point> {
		int x, cost;

		public Point(int x, int cost) {
			this.x = x;
			this.cost = cost;
		}

		@Override
		public int compareTo(Point o) {
			return this.cost - o.cost; // 비용 기준 오름차순 정렬
		}
	}
}