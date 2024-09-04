import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static final int DIV = 10_007;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];
		dp[1] = 1;
		for (int i = 2; i <= N; i++) {
			if (i == 2)
				dp[i] = 3;
			else if (i == 3)
				dp[i] = 5;
			else
				dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % DIV;
		}
		System.out.println(dp[N]);
	}
}