import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int[][] dp = new int[N][2]; // [][0]: 부분 수열의 길이, [][]: 부분 수열 중 이전 값의 인덱스
		for (int i = 0; i < N; i++) {
			dp[i][0] = 1;
			dp[i][1] = i;
		}

		int max = 1, maxIdx = 0;
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				// 현재 값이 이전 값보다 증가한다면
				if (arr[i] > arr[j]) {
					if (dp[i][0] <= dp[j][0]) {
						dp[i][0] = dp[j][0] + 1;
						dp[i][1] = j;
					}
				}
			}

			if (dp[i][0] > max) {
				max = dp[i][0];
				maxIdx = i;
			}
		}

		System.out.println(max);
		StringBuilder sb = new StringBuilder();
		int now = maxIdx;
		while (true) {
			sb.insert(0, arr[now] + " ");
			if (now == dp[now][1]) // 자기 자신을 가리킴 == 부분 수열의 첫 번째
				break;
			now = dp[now][1];
		}
		System.out.println(sb);
	}
}