import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] arr, record;
	static boolean[] used;
	static StringBuilder sb = new StringBuilder();
	static HashSet<String> answer = new LinkedHashSet<>(); // 입력 순서대로 유지

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

		used = new boolean[N];
		record = new int[M];
		dfs(0);
		sb.setLength(0);
		for (String a : answer) {
			sb.append(a).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int depth) {
		if (depth == M) {
			sb.setLength(0); // StringBuilder 초기화
			for (int r : record) {
				sb.append(r).append(" ");
			}
			answer.add(sb.toString()); // 중복 제거를 위해 HashSet에 저장하기
			return;
		}

		for (int i = 0; i < N; i++) {
			if (used[i]) continue;

			used[i] = true;
			record[depth] = arr[i];
			dfs(depth + 1);
			used[i] = false;
		}
	}
}
