import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static final int INF = 1_000_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[] coins = new int[n];
		for (int i = 0; i < n; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(coins);

		int[] dp = new int[k + 1];
		Arrays.fill(dp, INF);

		for (int i = 0; i < n; i++) {
			int coin = coins[i];
			for (int j = coin; j <= k; j++) {
				if (coin == j) {
					dp[j] = 1;
				} else {
					dp[j] = Math.min(dp[j], dp[j - coin] + 1);
				}
			}
		}
		System.out.println(dp[k] == INF ? -1 : dp[k]);
	}
}