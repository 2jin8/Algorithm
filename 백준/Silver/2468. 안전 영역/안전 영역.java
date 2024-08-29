import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int minH = 100, maxH = 1; // 지역의 최소/최대 높이

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (minH > map[i][j]) minH = map[i][j]; // 최소 높이 구하기
				else if (maxH < map[i][j]) maxH = map[i][j]; // 최대 높이 구하기
			}
		}

		int maxArea = 1; // 물에 잠기지 않을 때 안전 영역은 1개
		for (int h = minH; h < maxH; h++) {
			int area = 0;
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && map[i][j] > h) {
						area++;
						bfs(i, j, h);
					}
				}
			}
			maxArea = Math.max(maxArea, area);
		}
		System.out.println(maxArea);
	}

	static void bfs(int x, int y, int h) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(x, y));
		visited[x][y] = true;

		int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };
		while (!queue.isEmpty()) {
			Point now = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;

				if (!visited[nx][ny] && map[nx][ny] > h) {
					queue.offer(new Point(nx, ny));
					visited[nx][ny] = true;
				}
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