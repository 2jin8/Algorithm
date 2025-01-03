import java.util.Scanner;

public class Solution {

	static int N, W, ans;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			N = scan.nextInt();
			W = scan.nextInt();

			arr = new int[N]; // N개 집합의 원소
			for (int i = 0; i < N; i++) {
				arr[i] = scan.nextInt();
			}

			ans = 0;
			subsetSum(0, 0);
			System.out.println(ans);
		}
	}

	static void subsetSum(int depth, int sum) {
		if (sum == W) {
			ans++;
			return;
		}

		if (sum > W || depth >= N)
			return;

		// 현재 원소 선택 O
		subsetSum(depth + 1, sum + arr[depth]);

		// 현재 원소 선택 X
		subsetSum(depth + 1, sum);
	}
}