import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, max;
	static int[] arr, idx;
	static boolean[] used;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		used = new boolean[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		idx = new int[N]; // 순서 기록용 배열
		dfs(0);
		System.out.println(max);
	}

	// 배열의 순서를 정하기! (= 순열 만들기)
	static void dfs(int depth) {
		if (depth == N) {
			int sum = 0;
			for (int i = 0; i < N - 1; i++) {
				sum += Math.abs(arr[idx[i]] - arr[idx[i + 1]]);
			}
			max = Math.max(max, sum);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (used[i]) continue;

			used[i] = true;
			idx[depth] = i;
			dfs(depth + 1);
			used[i] = false;
		}
	}
}