import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N, L, maxScore; // N: 재료의 수, L: 제한 칼로리
	static int[] tastes;
	static int[] kcals;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			maxScore = 0;
			tastes = new int[N];
			kcals = new int[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				tastes[i] = Integer.parseInt(st.nextToken());
				kcals[i] = Integer.parseInt(st.nextToken());
			}
			dfs(0, 0, 0);
			sb.append("#").append(t).append(" ").append(maxScore).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int depth, int taste, int kcal) {
		// 칼로리 제한을 넘어가면 종료하기
		if (kcal > L) {
			return;
		}

		if (depth == N) {
			if (taste > maxScore)
				maxScore = taste;
			return;
		}

		// 현재 재료 선택 O
		dfs(depth + 1, taste + tastes[depth], kcal + kcals[depth]);

		// 현재 재료 선택 X
		dfs(depth + 1, taste, kcal);
	}
}