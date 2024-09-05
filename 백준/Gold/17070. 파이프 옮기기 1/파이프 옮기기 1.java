import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, ans = 0;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { 0, 1, 1 }, dy = { 1, 1, 0 }; // 가로, 대각선, 세로
	static int[] cx = { 0, -1 }, cy = { -1, 0 }; // 대각선 확인용

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		visited = new boolean[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(1, 2, 0); // 파이프의 한쪽 끝 좌표는 (1, 2) & 가로
		System.out.println(ans);
	}

	static void dfs(int x, int y, int d) { // d: 0(가로), 1(대각선), 2(세로)
		if (x == N && y == N) {
			ans++;
			return;
		}

		visited[x][y] = true;

		int start = 0, end = 2;
		if (d == 0) { // 가로 방향으로 놓여져있으면 가로, 대각선으로 이동
			end--;
		} else if (d == 2) { // 세로 방향으로 놓여져있으면 세로, 대각선으로 이동
			start++;
		}

		for (int i = start; i <= end; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			// 범위 벗어난 경우
			if (nx <= 0 || ny <= 0 || nx > N || ny > N)
				continue;

			// 이미 방문했거나 벽이 있는 경우
			if (visited[nx][ny] || map[nx][ny] == 1)
				continue;

			if (i == 1) { // 대각선 방향으로 이동하는 것은 대각선 위, 대각선 왼쪽에 벽이 있는지 확인해야 함
				// 벽이 있으면 대각선으로 이동하지 못함
				if (map[nx - 1][ny] == 1 || map[nx][ny - 1] == 1)
					continue;
			}
			dfs(nx, ny, i); // 가로, 세로 방향으로 이동하는 것은 그냥 이동 가능하므로 체크 X
		}
		visited[x][y] = false;
	}
}