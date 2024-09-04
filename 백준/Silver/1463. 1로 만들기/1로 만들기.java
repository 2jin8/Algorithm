import java.io.BufferedReader;
import java.io.InputStreamReader;

// DP
public class Main {

	static int N;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];

		for (int i = 2; i <= N; i++) {
			// 1을 빼는 경우
			dp[i] = dp[i - 1] + 1;

			// 3으로 나누는 경우
			if (i % 3 == 0)
				dp[i] = Math.min(dp[i], dp[i / 3] + 1); // 둘 중 작은 값으로 갱신

			// 2로 나누는 경우
			if (i % 2 == 0)
				dp[i] = Math.min(dp[i], dp[i / 2] + 1); // 둘 중 작은 값으로 갱신
		}
		System.out.println(dp[N]);
	}
}