import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int N, MAX = Integer.MAX_VALUE;
	static int[] arr, dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		// 최장 부분 증가 수열
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			st = new StringTokenizer(br.readLine());
			dp = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.fill(dp, MAX);
			dp[0] = arr[0];
			for (int i = 1; i < N; i++) {
				int idx = Arrays.binarySearch(dp, arr[i]);
				if (idx < 0) {
					idx = -1 * idx - 1;
				}
				dp[idx] = arr[i];
			}

			int len = 0;
			for (int i = 0; i < N; i++) {
				if (dp[i] == MAX) // 값이 기록되지 않았으면 LIS 종료
					break;
				len++; // LIS 길이 세기
			}
			sb.append("#").append(t).append(" ").append(len).append("\n");
		}
		System.out.println(sb);
	}
}