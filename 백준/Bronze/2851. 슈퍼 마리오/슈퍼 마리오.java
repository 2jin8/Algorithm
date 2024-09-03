import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static final int N = 10, SUM = 100;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		int minDiff = Integer.MAX_VALUE; // 둘의 차이
		int maxSum = 0; // 최대 합
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = 0; j <= i; j++) {
				sum += arr[j];
			}
			int diff = Math.abs(SUM - sum);
			if (diff <= minDiff) {
				minDiff = diff; // 차이 기록
				maxSum = Math.max(maxSum, sum); // 점수 기록
			}
		}
		System.out.println(maxSum);
	}
}