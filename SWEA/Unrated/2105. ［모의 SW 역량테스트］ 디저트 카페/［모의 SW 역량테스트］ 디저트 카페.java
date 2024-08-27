import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution {

	static int N, maxDessert;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dx = { 1, 1, -1, -1, }, dy = { 1, -1, -1, 1 };
	static HashSet<Integer> cafes = new HashSet<Integer>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			maxDessert = 0;
			for (int i = 0; i < N - 2; i++) {
				for (int j = 1; j < N - 1; j++) {
					visited = new boolean[N][N];
					cafes.clear();
					visited[i][j] = true;
					cafes.add(arr[i][j]);
					dfs(i, j, i, j, 0, 1);
				}
			}
			sb.append("#").append(t).append(" ").append(maxDessert == 0 ? -1 : maxDessert).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int sx, int sy, int x, int y, int dir, int depth) {
		// 이전에 탐색한 방향을 시작 방향으로
		for (int d = dir; d < 4; d++) {
			int nx = x + dx[d], ny = y + dy[d];
			// 시작점에 도착한 경우
			if (nx == sx && ny == sy) {
				if (depth > 2) { // 같은 방향으로 돌아온 경우가 아닐 때
					maxDessert = Math.max(maxDessert, depth);
				}
				return;
			}

			// 범위를 벗어난 경우
			if (nx < 0 || ny < 0 || nx >= N || ny >= N)
				continue;

			// 이미 방문한 곳이거나 같은 숫자의 디저트를 팔고 있는 경우
			if (cafes.contains(arr[nx][ny]) || visited[nx][ny])
				continue;

			cafes.add(arr[nx][ny]);
			visited[nx][ny] = true;
			dfs(sx, sy, nx, ny, d, depth + 1);
			visited[nx][ny] = false;
			cafes.remove(arr[nx][ny]);
		}
	}
}