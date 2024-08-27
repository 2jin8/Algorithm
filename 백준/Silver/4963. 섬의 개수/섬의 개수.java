import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int w, h; // w: 너비, h: 높이
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if (w == 0 && h == 0) {
				System.out.println(sb);
				break;
			}

			map = new int[h][w];
			visited = new boolean[h][w];
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int island = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					// 방문하지 않았고 섬인 경우 BFS 탐색
					if (!visited[i][j] && map[i][j] == 1) {
						bfs(i, j);
						island++;
					}
				}
			}
			sb.append(island).append("\n");
		}
	}

	static void bfs(int sx, int sy) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(sx, sy));
		visited[sx][sy] = true;

		// 가로, 세로, 대각선으로 연결되어 있는 섬을 찾아야 함
		int[] dx = { 1, -1, 0, 0, 1, 1, -1, -1 };
		int[] dy = { 0, 0, 1, -1, 1, -1, 1, -1 };
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			int x = now.x, y = now.y;

			for (int i = 0; i < 8; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				// 범위를 벗어나는 경우
				if (nx < 0 || ny < 0 || nx >= h || ny >= w)
					continue;

				// 이미 방문했거나 섬이 아닌 바다인 경우
				if (visited[nx][ny] || map[nx][ny] == 0)
					continue;

				queue.offer(new Point(nx, ny));
				visited[nx][ny] = true;
			}
		}
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}