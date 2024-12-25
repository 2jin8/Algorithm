import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, totalSum, ans = Integer.MAX_VALUE;
	static int[] d;
	static int[][] arr;
	static boolean[][] border;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1][N + 1];
		StringTokenizer st = null;

		totalSum = 0;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				totalSum += arr[i][j];
			}
		}

		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= N; y++) {
				for (int d1 = 1; d1 <= N; d1++) {
					for (int d2 = 1; d2 <= N; d2++) {
						if ((x + d1 + d2 > N) || (y - d1 < 1) || (y + d2 > N))
							continue;

						splitArea(x, y, d1, d2);
					}
				}
			}
		}
		System.out.println(ans);
	}

	static void splitArea(int x, int y, int d1, int d2) {
		border = new boolean[N + 1][N + 1];

		// 1번 경계선
		for (int i = x, j = y; i <= x + d1 && j >= y - d1; i++, j--) {
			border[i][j] = true;
		}

		// 2번 경계선
		for (int i = x, j = y; i <= x + d2 && j <= y + d2; i++, j++) {
			border[i][j] = true;
		}

		// 3번 경계선
		for (int i = x + d1, j = y - d1; i <= x + d1 + d2 && j <= y - d1 + d2; i++, j++) {
			border[i][j] = true;
		}

		// 4번 경계선
		for (int i = x + d2, j = y + d2; i <= x + d2 + d1 && j >= y + d2 - d1; i++, j--) {
			border[i][j] = true;
		}

		int[] sum = new int[6];
		// 1구역 인구 수
		for (int i = 1; i < x + d1; i++) {
			for (int j = 1; j <= y; j++) {
				if (border[i][j])
					break;
				sum[1] += arr[i][j];
			}
		}
		// 2구역 인구 수
		for (int i = 1; i <= x + d2; i++) {
			for (int j = N; j > y; j--) {
				if (border[i][j])
					break;
				sum[2] += arr[i][j];
			}
		}
		// 3구역 인구 수
		for (int i = x + d1; i <= N; i++) {
			for (int j = 1; j < y - d1 + d2; j++) {
				if (border[i][j])
					break;
				sum[3] += arr[i][j];
			}
		}
		// 4구역 인구 수
		for (int i = x + d2 + 1; i <= N; i++) {
			for (int j = N; j >= y - d1 + d2; j--) {
				if (border[i][j])
					break;
				sum[4] += arr[i][j];
			}
		}

		sum[5] = totalSum - (sum[1] + sum[2] + sum[3] + sum[4]);
		Arrays.sort(sum);
		ans = Math.min(ans, sum[5] - sum[1]);
	}
}