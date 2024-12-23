import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static final int INF = Integer.MAX_VALUE;
	static int N, check, ans;
	static int[] nums, record;
	static boolean[] used;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		check = Integer.parseInt(str);
		
		N = str.length();
		nums = new int[N];
		record = new int[N];
		used = new boolean[N];
		for (int i = 0; i < N; i++) {
			nums[i] = str.charAt(i) - '0';
		}
		ans = INF;
		dfs(0);
		System.out.println(ans == INF? 0 : ans);
	}

	static void dfs(int depth) {
		if (depth == N) {
			int num = 0;
			for (int i = 0; i < N; i++) {
				num = num * 10 + record[i];
			}
			
			// check보다 큰 수 중, 가장 작은 수 구하기
			if (num > check) ans = Math.min(ans, num);
			return;
		}

		for (int i = 0; i < N; i++) {
			// 이미 사용했거나 첫 글자가 0이면 넘어가기
			if (used[i] || (depth == 0 && nums[i] == 0))
				continue;

			used[i] = true;
			record[depth] = nums[i];
			dfs(depth + 1);
			used[i] = false;
		}
	}
}