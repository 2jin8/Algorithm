import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int K, W, H;
	static int[][] map;
	static boolean[][][] visited; // 말처럼 이동하는 횟수가 달라짐 > 상태 변경..
	static int[] mDx = { 1, -1, 0, 0 }, mDy = { 0, 0, 1, -1 };
	static int[] hDx = { -2, -2, 2, 2, -1, -1, 1, 1 }, hDy = { -1, 1, -1, 1, -2, 2, -2, 2 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine()); // 말처럼 이동할 수 있는 횟수
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		visited = new boolean[K + 1][H][W];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(bfs());

	}

	static int bfs() {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(0, 0, 0, 0));
		visited[0][0][0] = true;

		while (!queue.isEmpty()) {
			Point now = queue.poll();
			int x = now.x, y = now.y, k = now.k;
			if (x == H - 1 && y == W - 1)
				return now.dist;

			// 원숭이처럼 이동하기
			for (int i = 0; i < 4; i++) {
				int nx = x + mDx[i];
				int ny = y + mDy[i];
				if (nx < 0 || ny < 0 || nx >= H || ny >= W)
					continue;

				// 방문 X & 벽 X
				if (!visited[k][nx][ny] && map[nx][ny] == 0) {
					queue.offer(new Point(nx, ny, k, now.dist + 1));
					visited[k][nx][ny] = true;
				}
			}

			// 말처럼 이동할 횟수가 남은 경우
			if (k < K) {
				for (int i = 0; i < 8; i++) {
					int nx = x + hDx[i];
					int ny = y + hDy[i];
					if (nx < 0 || ny < 0 || nx >= H || ny >= W)
						continue;

					if (!visited[k + 1][nx][ny] && map[nx][ny] == 0) {
						queue.offer(new Point(nx, ny, k + 1, now.dist + 1));
						visited[k + 1][nx][ny] = true;
					}
				}
			}
		}
		return -1;
	}

	static class Point {
		int x, y, k, dist;

		public Point(int x, int y, int k, int dist) {
			this.x = x;
			this.y = y;
			this.k = k;
			this.dist = dist;
		}
	}
}