import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, ans; // ans: 손해보지 않으면서 가장 많은 서비스를 제공받는 집들의 수
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					homeCnt(i, j);
				}
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static void homeCnt(int x, int y) {
		for (int k = 1; k <= N + 1; k++) {
			int cost = k * k + (k - 1) * (k - 1);
			visited = new boolean[N][N];
			int home = bfs(x, y, k);
			if (home * M >= cost) {
				ans = Math.max(ans, home);
			}
		}
	}

	static int bfs(int x, int y, int k) {
		Queue<Pos> queue = new ArrayDeque<>();
		queue.offer(new Pos(x, y));
		visited[x][y] = true;

		int cnt = 0, home = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int s = 0; s < size; s++) {
				Pos now = queue.poll();
				if (map[now.x][now.y] == 1)
					home++;

				for (int i = 0; i < 4; i++) {
					int nx = now.x + dx[i];
					int ny = now.y + dy[i];
					if (nx < 0 || ny < 0 || nx >= N || ny >= N)
						continue;

					if (!visited[nx][ny]) {
						queue.offer(new Pos(nx, ny));
						visited[nx][ny] = true;
					}
				}
			}
			if (++cnt == k)
				break;
		}
		return home;
	}

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}