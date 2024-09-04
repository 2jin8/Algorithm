import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static final int DIV = 10_007;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			if (i <= 3)
				dp[i] = i;
			else
				dp[i] = (dp[i - 1] + dp[i - 2]) % DIV;
		}
		System.out.println(dp[N]);
	}
}