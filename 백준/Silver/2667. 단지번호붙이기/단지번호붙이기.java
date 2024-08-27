import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class Main {

	static int N;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> ans = new ArrayList<>(); // 단지에 속하는 집의 수를 저장하기 위한 리스트
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 아직 방문하지 않았고 집이 있는 곳인 경우
				if (!visited[i][j] && map[i][j] == 1) {
					ans.add(bfs(i, j));
				}
			}
		}

		// 집의 수를 오름차순으로 정렬
		Collections.sort(ans);

		StringBuilder sb = new StringBuilder();
		sb.append(ans.size()).append("\n");
		for (int a : ans) {
			sb.append(a).append("\n");
		}
		System.out.println(sb);
	}

	static int bfs(int sx, int sy) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(sx, sy));
		visited[sx][sy] = true;

		int home = 0;
		int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			int x = now.x, y = now.y;
			home++;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				// 범위를 벗어난 경우
				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;

				// 이미 방문했거나 집이 없는 곳인 경우
				if (visited[nx][ny] || map[nx][ny] == 0)
					continue;

				queue.offer(new Point(nx, ny));
				visited[nx][ny] = true;
			}
		}
		return home;
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}