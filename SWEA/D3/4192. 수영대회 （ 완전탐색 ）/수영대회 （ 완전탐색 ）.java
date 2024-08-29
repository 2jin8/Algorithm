import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int[][] map; // 0: 이동 가능, 1: 이동 불가능
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			StringTokenizer st = new StringTokenizer(br.readLine());
			Point start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
			st = new StringTokenizer(br.readLine());
			Point end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
			sb.append("#").append(t).append(" ").append(dfs(start, end)).append("\n");
		}
		System.out.println(sb);
	}

	static int dfs(Point start, Point end) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(start);
		visited[start.x][start.y] = true;

		int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			if (now.x == end.x && now.y == end.y)
				return now.dist;

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;

				if (!visited[nx][ny] && map[nx][ny] == 0) {
					queue.offer(new Point(nx, ny, now.dist + 1));
					visited[nx][ny] = true;
				}
			}
		}
		return -1;
	}

	static class Point {
		int x, y, dist;

		public Point(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
}