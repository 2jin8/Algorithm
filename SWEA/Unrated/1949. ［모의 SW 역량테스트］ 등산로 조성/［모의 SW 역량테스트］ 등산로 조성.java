import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

	static int N, K, ans; // K: 최대 공사 가능한 깊이
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };
	static ArrayList<Pos> topList = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			visited = new boolean[N][N];

			int maxH = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (maxH < map[i][j]) {
						maxH = map[i][j];
					}
				}
			}

			ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 가장 높은 봉우리일 때
					if (map[i][j] == maxH) {
						// 등산로의 길이 구하기
						visited[i][j] = true;
						dfs(i, j, 1, false); // 자기 자신 시작 = 등산로 길이 1
						visited[i][j] = false;
					}
				}
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int x, int y, int route, boolean crash) {

		visited[x][y] = true;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny])
				continue;

			// 낮은 지형으로 이동 가능할 때
			if (map[x][y] > map[nx][ny]) {
				dfs(nx, ny, route + 1, crash);
			}
			// 낮은 지형으로 이동 불가능할 때, 공사하지 않았다면 현재 높이 - 1로 깎기
			else if (!crash && map[nx][ny] - (map[x][y] - 1) <= K) { // K만큼만 깎을 수 있음
				int tmp = map[nx][ny];
				map[nx][ny] = map[x][y] - 1;
				dfs(nx, ny, route + 1, true);
				map[nx][ny] = tmp;
			}
		}
		visited[x][y] = false;
		if (ans < route)
			ans = route;
	}

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}