import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] time = new int[n + 1];
		int[] pay = new int[n + 1];
		int[] dp = new int[n + 1];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			pay[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < n; i++) {
			if (i + time[i] <= n) {
				dp[i + time[i]] = Math.max(dp[i + time[i]], dp[i] + pay[i]);
			}
			dp[i + 1] = Math.max(dp[i], dp[i + 1]);
		}
		System.out.println(dp[n]);
	}
}