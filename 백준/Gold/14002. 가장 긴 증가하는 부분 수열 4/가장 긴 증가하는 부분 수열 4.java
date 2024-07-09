import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[] arr = new int[n];
		StringTokenizer tokenizer = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(tokenizer.nextToken());
		}

		int[][] dp = new int[n][2];
		for (int i = 0; i < n; i++) {
			dp[i][0] = 1;
			dp[i][1] = -1;
		}
		
		int max = dp[0][0], maxIdx = 0;
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j] && dp[i][0] <= dp[j][0]) {
					dp[i][0] = dp[j][0] + 1;
					dp[i][1] = j;
				}
			}
			if (max < dp[i][0]) {
				max = dp[i][0];
				maxIdx = i;
			}
		}

		StringBuilder builder = new StringBuilder();
		int now = maxIdx;
		while (now != -1) {
			builder.insert(0, arr[now] + " ");
			now = dp[now][1];
		}
		builder.insert(0, max + "\n"); 
		System.out.println(builder);
	}
}