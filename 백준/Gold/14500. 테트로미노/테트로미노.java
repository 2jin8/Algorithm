import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M, ans;
	static int[][] arr;
	static Point[] points;
	static boolean[][] visited;
	static int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 시간초과 날 것 같지만 일단 ㄲ
		visited = new boolean[N][M];
		points = new Point[3];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				points[0] = new Point(i, j);
				visited[i][j] = true;
				dfs(1, arr[i][j], i, j);
				visited[i][j] = false;
			}
		}
		System.out.println(ans);
	}

	static void dfs(int depth, int total, int x, int y) {
		// 3개 칸을 고른 경우
		if (depth == 3) {
			// 나머지 칸 고르기
			ans = Math.max(ans, total + bfs());
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny])
				continue;

			visited[nx][ny] = true;
			points[depth] = new Point(nx, ny);
			dfs(depth + 1, total + arr[nx][ny], nx, ny);
			visited[nx][ny] = false;
		}
	}

	static int bfs() {
		int max = 0;
		for (int i = 0; i < 3; i++) {
			Point now = points[i];
			for (int j = 0; j < 4; j++) {
				int nx = now.x + dx[j];
				int ny = now.y + dy[j];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny])
					continue;

				// 선택할 공간의 값이 더 큰 것으로 선택하기
				max = Math.max(max, arr[nx][ny]);
			}
		}
		return max;
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}