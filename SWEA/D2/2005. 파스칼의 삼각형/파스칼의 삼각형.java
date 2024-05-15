import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine());
			int[][] dp = new int[n][n];
			dp[0][0] = 1;
			for (int i = 1; i < n; i++) {
				for (int j = 0; j <= i; j++) {
					if (j == 0 || i == j)
						dp[i][j] = 1;
					else
						dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
				}
			}

			sb.append("#").append(t).append("\n");
			for (int i = 0; i < n; i++) {
				for (int j = 0; j <= i; j++) {
					sb.append(dp[i][j]).append(" ");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
	}
}