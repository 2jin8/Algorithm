import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] arr, record;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		record = new int[M];
		dfs(0);
		System.out.println(sb);
	}

	// 중복 순열
	static void dfs(int depth) {
		if (depth == M) {
			for (int r : record) {
				sb.append(r).append(" ");
			}
			sb.append("\n");
			return;
		}

		// 같은 depth 일 때, 이전에 선택된 수와 같지 않게 선택해야 함
		int before = -1;
		for (int i = 0; i < N; i++) {
			if (before != arr[i]) {
				before = arr[i];
				record[depth] = arr[i];
				dfs(depth + 1);
			}
		}
	}
}
