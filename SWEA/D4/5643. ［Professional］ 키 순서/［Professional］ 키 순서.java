import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N, M;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			map = new int[N + 1][N + 1];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				map[a][b] = 1;
			}

			for (int i = 1; i <= N; i++) {
				map[i][0] = -1;
			}

			for (int i = 1; i <= N; i++) {
				// 해당 정점에서 이미 탐색을 완료한 경우라면 넘어가기
				if (map[i][0] != -1)
					continue;

				dfs(i);
			}

			// 자신보다 키가 작은 사람의 수 구하기
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					map[0][j] += map[i][j];
				}
			}

			int ans = 0;
			for (int i = 1; i <= N; i++) {
				// 자신보다 키가 작은 사람의 수 + 키가 큰 사람의 수가 N-1 이면 순서를 알 수 있음
				if (map[i][0] + map[0][i] == N - 1)
					ans++;
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int x) {
		for (int i = 1; i <= N; i++) {
			// 연결되어 있지 않은 정점이면 넘어가기
			if (map[x][i] == 0)
				continue;

			// 정점 i에서 탐색한 적이 없는 경우
			if (map[i][0] == -1) {
				dfs(i);
			}

			// 정점 i에서 갈 수 있는 경로가 1개 이상이면 해당 배열의 값을 현재 x에 더해주기
			if (map[i][0] > 0) {
				for (int j = 1; j <= N; j++) {
					if (map[i][j] != 0) {
						map[x][j] = 1;
					}
				}
			}
		}

		// 연결된 정점 개수 구하기
		map[x][0] = 0;
		for (int j = 1; j <= N; j++) {
			map[x][0] += map[x][j];
		}
	}
}