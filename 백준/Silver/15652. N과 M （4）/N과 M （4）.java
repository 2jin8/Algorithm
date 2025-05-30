import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] nums;
	static StringBuilder sb = new StringBuilder();

	// 중복 조합
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[M]; // 순열 기록
		dfs(0, 1);
		System.out.println(sb);
	}

	static void dfs(int depth, int start) {
		if (depth == M) {
			for (int num : nums) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = start; i <= N; i++) {
			nums[depth] = i;
			dfs(depth + 1, i);
		}
	}
}