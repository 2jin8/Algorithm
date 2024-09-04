import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N, ans;
	static int[] weights, nums;
	static boolean[] used;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			weights = new int[N]; // 무게추의 무게
			nums = new int[N];
			used = new boolean[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				weights[i] = Integer.parseInt(st.nextToken());
			}

			ans = 0;
			dfs(0);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	// 무게추를 올리는 순서
	static void dfs(int depth) {
		if (depth == N) {
			// 왼쪽/오른쪽 나눠서 올리기
			leftOrRight(0, 0, 0);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (used[i])
				continue;

			nums[depth] = weights[i];
			used[i] = true;
			dfs(depth + 1);
			used[i] = false;
		}
	}

	static void leftOrRight(int depth, int left, int right) {
		// 오른쪽 무게 총합이 왼쪽 무게 총합보다 크면 안됨
		if (left < right)
			return;

		if (depth == N) {
			ans++;
			return;
		}

		leftOrRight(depth + 1, left + nums[depth], right);
		leftOrRight(depth + 1, left, right + nums[depth]);
	}
}