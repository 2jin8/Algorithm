import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	static int N;
	static char[][] pictureO, pictureX; // pictureO: 적록색약 O, pictureX: 적록색약 X
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		pictureO = new char[N][N];
		pictureX = new char[N][N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				pictureX[i][j] = line.charAt(j);
				// 적록색약인 사람의 그림 중 초록색을 빨간색으로 변경 ('R' = 'G')
				pictureO[i][j] = pictureX[i][j] == 'G' ? 'R' : pictureX[i][j];
			}
		}

		// 적록색약 X
		int area = 0;
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					area++;
					bfs(i, j, pictureX);
				}
			}
		}
		System.out.print(area + " ");

		// 적록색약 O
		area = 0;
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					area++;
					bfs(i, j, pictureO);
				}
			}
		}
		System.out.println(area);
	}

	static void bfs(int sx, int sy, char[][] picture) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(sx, sy));
		visited[sx][sy] = true;

		int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };
		char c = picture[sx][sy];
		while (!queue.isEmpty()) {
			Point now = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				// 범위를 벗어나는 경우
				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;

				// 이미 방문했거나 같은 구역이 아닌 경우
				if (visited[nx][ny] || picture[nx][ny] != c)
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