import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, cheese;
	static int[][] map, tmpMap;
	static boolean[][] visited;
	static int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };
	static Queue<Point> queue = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		tmpMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 치즈 개수 세기
				if (map[i][j] == 1)
					cheese++;
			}
		}

		int hour = 0;
		while (true) {
			hour++;
			// 초기화
			visited = new boolean[N][M];
			queue.clear();
			copyArray(map, tmpMap);

			int melt = bfs();
			if (cheese - melt == 0) { // 치즈가 다 녹은 경우
				System.out.println(hour);
				System.out.println(melt);
				break;
			}

			cheese -= melt;
			copyArray(tmpMap, map);
		}
	}

	static int bfs() {
		// (0, 0)에서 탐색 시작 (공기 영역을 탐색)
		queue.offer(new Point(0, 0));
		visited[0][0] = true;

		int melt = 0;
		while (!queue.isEmpty()) {
			Point now = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny])
					continue;

				if (map[nx][ny] == 1) { // 공기와 닿은 치즈는 녹음
					melt++;
					tmpMap[nx][ny] = 0;
					visited[nx][ny] = true;
				} else {
					queue.offer(new Point(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}
		return melt;
	}

	static void copyArray(int[][] from, int[][] to) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				to[i][j] = from[i][j];
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