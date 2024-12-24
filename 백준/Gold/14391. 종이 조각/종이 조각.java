import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M, maxScore;
	static int[][] arr;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = line.charAt(j) - '0';
			}
		}
		dfs(0, 0, 0);
		System.out.println(maxScore);
	}

	static void dfs(int x, int y, int score) {
		if (x >= N) {
			maxScore = Math.max(maxScore, score);
			return;
		} else if (y >= M) {
			dfs(x + 1, 0, score);
			return;
		}

		if (visited[x][y]) {
			dfs(x, y + 1, score);
			return;
		}

		// 현재 칸만 선택
		visited[x][y] = true;
		dfs(x, y + 1, score + arr[x][y]);

		// 가로로 선택
		for (int i = 1; i < M; i++) {
			if (y + i >= M || visited[x][y + i])
				break;

			int tmpScore = arr[x][y];
			for (int j = 1; j <= i; j++) {
				tmpScore = tmpScore * 10 + arr[x][y + j];
				visited[x][y + i] = true;
			}
			dfs(x, y + i + 1, score + tmpScore);
			for (int j = 1; j <= i; j++) {
				visited[x][y + j] = false;
			}
		}

		// 세로로 선택
		for (int i = 1; i < N; i++) {
			if (x + i >= N || visited[x + i][y])
				break;

			int tmpScore = arr[x][y];
			for (int j = 1; j <= i; j++) {
				tmpScore = tmpScore * 10 + arr[x + j][y];
				visited[x + j][y] = true;
			}
			dfs(x, y + 1, score + tmpScore);
			for (int j = 1; j <= i; j++) {
				visited[x + j][y] = false;
			}
		}
		visited[x][y] = false;
	}
}