import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	static final int N = 100;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= 10; t++) {
			int testCase = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer stringTokenizer = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				}
			}
			int max = Math.max(Math.max(sumRow(), sumCol()), Math.max(sumCrossLeft(), sumCrossRight()));
			sb.append('#').append(testCase).append(' ').append(max).append('\n');
		}
		System.out.println(sb.toString());
	}

	static int sumRow() { // 가로
		int max = 0;
		for (int i = 0; i < N; i++) {
			int total = 0;
			for (int j = 0; j < N; j++) {
				total += arr[i][j];
			}
			max = Math.max(max, total);
		}

		return max;
	}

	static int sumCol() { // 세로
		int max = 0;
		for (int j = 0; j < N; j++) {
			int total = 0;
			for (int i = 0; i < N; i++) {
				total += arr[i][j];
			}
			max = Math.max(max, total);
		}
		return max;
	}

	static int sumCrossLeft() { // 좌대각선
		int max = 0;
		for (int i = 0; i < N; i++) {
			max += arr[i][i];
		}
		return max;
	}

	static int sumCrossRight() { // 우대각선
		int max = 0;
		for (int i = 0; i < N; i++) {
			max += arr[i][N - i - 1];
		}
		return max;
	}
}
