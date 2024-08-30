import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			String line = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = line.charAt(j - 1) - '0';
			}
		}
		System.out.println(bfs());
	}

	static int bfs() {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(1, 1));
		map[1][1] = 2; // 방문 체크를 위해 2로 초기화 (나중에 거리에서 1을 빼줘야 함)

		int dist = -1;
		int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			if (now.x == N && now.y == M) {
				dist = map[now.x][now.y] - 1;
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (nx <= 0 || ny <= 0 || nx > N || ny > M)
					continue;

				if (map[nx][ny] == 1) {
					queue.offer(new Point(nx, ny));
					map[nx][ny] = map[now.x][now.y] + 1;
				}
			}
		}
		return dist;
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}