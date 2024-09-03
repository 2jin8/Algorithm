import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
	static int N;
	static int[][] arr, minDist;
	static boolean[][] visited;
	static int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			arr = new int[N][N];
			minDist = new int[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				char[] chars = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					arr[i][j] = chars[j] - '0';
				}
			}
			sb.append("#").append(t).append(" ").append(getMinDist(0, 0, N - 1, N - 1)).append("\n");
		}
		System.out.println(sb);
	}

	static int getMinDist(int sx, int sy, int ex, int ey) {
		final int INF = Integer.MAX_VALUE;
		// 거리 배열 초기화
		for (int i = 0; i < N; i++) {
			Arrays.fill(minDist[i], INF);
		}

		PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.w, n2.w));

		minDist[sx][sy] = 0;
		pq.offer(new Node(sx, sy, minDist[sx][sy]));

		while (!pq.isEmpty()) {
			Node now = pq.poll();
			int x = now.x, y = now.y;
			if (x == ex && y == ey)
				return now.w;

			// 이미 방문한 경우
			if (visited[x][y])
				continue;

			// 방문 처리
			visited[x][y] = true;

			// 네 방향 탐색
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny])
					continue;

				// 저장된 최단 경로보다 짧다면 갱신
				if (minDist[nx][ny] > now.w + arr[nx][ny]) {
					minDist[nx][ny] = now.w + arr[nx][ny];
					pq.offer(new Node(nx, ny, minDist[nx][ny]));
				}
			}
		}
		return -1;
	}

	static class Node {
		int x, y, w;

		public Node(int x, int y, int w) {
			this.x = x;
			this.y = y;
			this.w = w;
		}
	}
}