import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N, K;
	static int[] weights, values;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			weights = new int[N + 1];
			values = new int[N + 1];
			dp = new int[N + 1][K + 1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				weights[i] = Integer.parseInt(st.nextToken());
				values[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 1; i <= N; i++) { // 물건 번호
				for (int j = 1; j <= K; j++) { // 가방 용량
					if (weights[i] > j) { // 가방의 용량보다 무게가 큰 경우
						dp[i][j] = dp[i - 1][j];
					} else { // 가방에 담을 수 있는 무게인 경우
						dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i]] + values[i]);
					}
				}
			}
			sb.append("#").append(t).append(" ").append(dp[N][K]).append("\n");
		}
		System.out.println(sb);
	}
}