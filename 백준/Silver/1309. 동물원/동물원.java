import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static final int DIV = 9901;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[][] dp = new int[n][3]; // [][2]: 놓지 않은 경우
		dp[0][0] = dp[0][1] = dp[0][2] = 1;
		for (int i = 1; i < n; i++) {
			dp[i][0] = (dp[i - 1][1] + dp[i - 1][2]) % DIV;
			dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % DIV;
			dp[i][2] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % DIV;
		}

		int sum = (dp[n - 1][0] + dp[n - 1][1] + dp[n - 1][2]) % DIV;
		System.out.println(sum);
	}
}