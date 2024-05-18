import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	static int N, K, count;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer stringTokenizer = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(stringTokenizer.nextToken());
			K = Integer.parseInt(stringTokenizer.nextToken());
			arr = new int[N];
			stringTokenizer = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(stringTokenizer.nextToken());
			}
			count = 0;
			dfs(0, 0);
			sb.append('#').append(t).append(' ').append(count).append('\n');
		}
		System.out.println(sb.toString());
	}

	static void dfs(int depth, int total) {
		if (depth == N) {
			if (total == K)
				count++;
			return;
		}

		dfs(depth + 1, total); // 현재 수를 선택하지 않는 경우
		dfs(depth + 1, total + arr[depth]); // 현재 수를 선택하는 경우
	}
}
