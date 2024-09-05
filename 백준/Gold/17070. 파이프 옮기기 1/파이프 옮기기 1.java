import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, ans = 0;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { 0, 1, 1 }, dy = { 1, 1, 0 }; // 가로, 대각선, 세로
	static int[] tx = { 0, -1 }, ty = { -1, 0 };

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

		// 가로, 세로는 그냥 이동하면 됨
		// 대각선은 세 방향을 모두 확인해야 함
		int start = 0, end = 2;
		if (d == 0) {
			end--;
		} else if (d == 2) {
			start++;
		}

		for (int i = start; i <= end; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx <= 0 || ny <= 0 || nx > N || ny > N)
				continue;

			if (visited[nx][ny] || map[nx][ny] == 1)
				continue;

			if (i != 1) {
				dfs(nx, ny, i);
			} else {
				// 현재 기준 위, 왼쪽 체크
				boolean check = false;
				for (int j = 0; j < 2; j++) {
					int nnx = nx + tx[j];
					int nny = ny + ty[j];
					if (nnx <= 0 || nny <= 0 || nnx > N || nny > N)
						continue;

					if (map[nnx][nny] == 1) {
						check = true;
						break;
					}
				}
				if (!check) {
					dfs(nx, ny, i);
				}
			}
		}
		visited[x][y] = false;
	}
}