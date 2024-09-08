import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// DFS + 메모이제이션
public class Solution {
	static int N, ansNum, ansCnt, MAX = 1001;
	static int[][] arr, dp;
	static int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			dp = new int[N][N];
			ansCnt = 0; // 이동할 수 있는 방의 개수가 최대인 것 출력
			ansNum = MAX; // 방에 적힌 수가 가장 적은 것 출력
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (dp[i][j] == 0) {
						dfs(i, j);
					}

					if (ansCnt < dp[i][j]) {
						ansCnt = dp[i][j];
						ansNum = arr[i][j];
					} else if (ansCnt == dp[i][j]) {
						ansNum = Math.min(ansNum, arr[i][j]);
					}
				}
			}
			sb.append("#").append(t).append(" ").append(ansNum).append(" ").append(ansCnt).append("\n");
		}
		System.out.println(sb);
	}

	static int dfs(int x, int y) {

		// 아직 방문하지 않은 경우
		if (dp[x][y] == 0) {
			dp[x][y] = 1; // 방문 처리 (자기 자신 방을 방문하는 경우)

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;

				if (arr[x][y] + 1 == arr[nx][ny]) { // 현재 방에 적힌 숫자보다 정확히 1 커야 함
					dp[x][y] += dfs(nx, ny);
					break; // 모든 방의 숫자는 다르므로 자신보다 1 큰 수는 오직 하나
				}
			}
		}
		return dp[x][y];
	}
}