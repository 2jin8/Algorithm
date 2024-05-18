import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	static int N, L, maxTaste;
	static int[] tastes, kcals;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stringTokenizer.nextToken());
			L = Integer.parseInt(stringTokenizer.nextToken());
			tastes = new int[N];
			kcals = new int[N];
			for (int i = 0; i < N; i++) {
				stringTokenizer = new StringTokenizer(br.readLine(), " ");
				tastes[i] = Integer.parseInt(stringTokenizer.nextToken());
				kcals[i] = Integer.parseInt(stringTokenizer.nextToken());
			}

			maxTaste = 0;
			dfs(0, 0, 0);
			sb.append('#').append(t).append(' ').append(maxTaste).append('\n');
		}
		System.out.println(sb.toString());
	}

	static void dfs(int idx, int totalKcal, int totalTaste) {
		if (totalKcal > L)
			return;
		if (idx == N) {
			maxTaste = Math.max(maxTaste, totalTaste);
			return;
		}

		dfs(idx + 1, totalKcal, totalTaste); // 현재 재료를 선택하지 않는 경우
		dfs(idx + 1, totalKcal + kcals[idx], totalTaste + tastes[idx]); // 현재 재료를 선택하는 경우
	}
}