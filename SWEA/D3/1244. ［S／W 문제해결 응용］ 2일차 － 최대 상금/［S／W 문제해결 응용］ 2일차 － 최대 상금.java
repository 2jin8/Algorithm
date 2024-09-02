import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N, M, max; // N: 자릿수, M: 교환횟수
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char[] chars = st.nextToken().toCharArray();
			N = chars.length;
			M = Integer.parseInt(st.nextToken());

			arr = new int[N];
			for (int i = 0; i < chars.length; i++) {
				arr[i] = chars[i] - '0';
			}
			if (M > N) M = N;
			max = 0;
			dfs(0);
			sb.append("#").append(t).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int depth) {
		// M번 교환 완료한 경우
		if (depth == M) {
			// 얻을 수 있는 상금 구하기
			int total = arr[0];
			for (int i = 1; i < N; i++) {
				total = total * 10 + arr[i];
			}
			max = Math.max(max, total); // 최댓값으로 갱신하기
			return;
		}

		// 교환할 숫자판 고르기 (조합)
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				swap(i, j); // 교환하기
				dfs(depth + 1);
				swap(j, i); // 교환 취소하기
			}
		}
	}

	static void swap(int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}