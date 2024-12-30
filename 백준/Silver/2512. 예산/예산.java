import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] budgets;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		budgets = new int[N];
		int maxBudget = 0;
		for (int i = 0; i < N; i++) {
			budgets[i] = Integer.parseInt(st.nextToken());
			maxBudget = Math.max(maxBudget, budgets[i]);
		}
		M = Integer.parseInt(br.readLine()); // 총 예산
		System.out.println(findMaxBudget(maxBudget));
	}

	static int findMaxBudget(int max) {
		int left = 1, right = max;
		int ans = 0;

		while (left <= right) {
			int mid = (left + right) / 2;

			// 예산이 남는다면 예산을 늘려보기
			// => 우측으로
			if (check(mid)) {
				ans = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return ans;
	}

	static boolean check(int maxBudget) {
		int total = 0;
		for (int budget : budgets) {
			// 상한액을 넘으면 상한액만큼만
			if (budget > maxBudget)
				total += maxBudget;
			else
				total += budget;
		}
		return total <= M; // 예산이 남으면 true, 부족하면 false
	}
}