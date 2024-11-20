import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N, M, cnt;
	static int[] icecream;
	static boolean[][] tasteInfo;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		icecream = new int[N + 1];
		tasteInfo = new boolean[N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			tasteInfo[a][b] = true;
			tasteInfo[b][a] = true;
		}
		dfs(0, 1);
		System.out.println(cnt);
	}

	// 순서 중요 X > 조합
	static void dfs(int depth, int start) {
		if (depth == 3) {
			int a = icecream[0], b = icecream[1], c = icecream[2];
			if (!tasteInfo[a][b] && !tasteInfo[a][c] && !tasteInfo[b][c])
				cnt++;
			return;
		}

		for (int i = start; i <= N; i++) {
			icecream[depth] = i;
			dfs(depth + 1, i + 1);
		}
	}
}