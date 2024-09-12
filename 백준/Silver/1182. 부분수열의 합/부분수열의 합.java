import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, S, ans;
	static int[] nums;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		dfs(0, 0, 0);
		System.out.println(ans);
	}
	
	static void dfs(int depth, int sum, int cnt) { // cnt: 공집합 확인을 위한 변수
		if (depth == N) {
			if (sum == S && cnt > 0) ans++;
			return;
		}
		
		// 현재 수 선택 O
		dfs(depth + 1, sum + nums[depth], cnt + 1);
		// 현재 수 선택 X
		dfs(depth + 1, sum, cnt);
	}
}