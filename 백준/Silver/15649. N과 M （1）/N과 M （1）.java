import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] ans;
	static boolean[] used;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 중복없이 M개를 고른 수열
		// 1 2 != 2 1 (순서가 중요! > 순열)
		ans = new int[M];
		used = new boolean[N + 1];
		dfs(0);
		System.out.println(sb);
	}

	static void dfs(int depth) {
		if (depth == M) {
			for (int a : ans) {
				sb.append(a).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 1; i <= N; i++) {
			// 이미 사용했다면 넘어가기 (중복 허용 X)
			if (used[i])
				continue;

			used[i] = true; // 사용
			ans[depth] = i; // 값 기록 (어짜피 덮어씌워지므로 초기화할 필요 X)
			dfs(depth + 1);
			used[i] = false; // 사용 취소
		}
	}
}