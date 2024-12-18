import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N + 1];
		int[] dp = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		int max = 0;
		for (int i = 1; i <= N; i++) {
			// 자기 자신 선택 X vs 자기 자신 선택 O & i-1번째 포도주 마심 vs 자기 자신 선택 O & i-2번째 포도주 마심
			if (i == 1)
				dp[1] = arr[1];
			else if (i == 2)
				dp[2] = arr[1] + arr[2];
			else if (i == 3)
				dp[i] = Math.max(dp[2], Math.max(arr[1], arr[2]) + arr[3]);
			else
				dp[i] = Math.max(dp[i - 1], arr[i] + Math.max(dp[i - 2], dp[i - 3] + arr[i - 1]));

			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}
}