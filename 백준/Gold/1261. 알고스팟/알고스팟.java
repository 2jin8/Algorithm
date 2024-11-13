import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, INF = 10000;
	static int[][] map, minCrash;
	static boolean[][] visited;
	static int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		minCrash = new int[M][N];
		visited = new boolean[M][N];

		for (int i = 0; i < M; i++) {
			char[] chars = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				map[i][j] = chars[j] - '0';
			}
		}

		for (int i = 0; i < M; i++) {
			Arrays.fill(minCrash[i], INF);
		}
		System.out.println(dijkstra(0, 0, M - 1, N - 1));
	}

	static int dijkstra(int sx, int sy, int ex, int ey) {
		PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.crash, n2.crash));
		pq.offer(new Node(sx, sy, 0));
		minCrash[sx][sy] = 0;

		while (!pq.isEmpty()) {
			Node now = pq.poll();
			int x = now.x, y = now.y;
			if (x == ex && y == ey)
				break;

			if (visited[x][y])
				continue;
			visited[x][y] = true;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || ny < 0 || nx >= M || ny >= N)
					continue;

				if (!visited[nx][ny] && minCrash[nx][ny] > minCrash[x][y] + map[nx][ny]) {
					minCrash[nx][ny] = minCrash[x][y] + map[nx][ny];
					pq.offer(new Node(nx, ny, minCrash[nx][ny]));
				}
			}
		}
		return minCrash[ex][ey];
	}

	static class Node {
		int x, y, crash;

		public Node(int x, int y, int crash) {
			this.x = x;
			this.y = y;
			this.crash = crash;
		}
	}
}