import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int total;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] weights = new int[N]; // 각 무게추의 무게를 저장하는 배열
			boolean[] used = new boolean[N]; // 무게추 사용 여부를 저장하는 배열

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				weights[i] = Integer.parseInt(st.nextToken());
			}

			total = 0;
			dfs(weights, used, N, 0, 0, 0);
			sb.append("#").append(t).append(" ").append(total).append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int[] weights, boolean[] used, int N, int depth, int left, int right) {
		// 모든 추를 다 사용한 경우
		if (depth == N) {
			// 오른쪽 무게 총합이 왼쪽 무게보다 작거나 같으므로 세기
			total++;
			return;
		}

		// 모든 무게 추를 양팔저울 위에 올리는 순서
		for (int i = 0; i < N; i++) {
			if (!used[i]) {
				// 현재 추 사용 처리하기
				used[i] = true;

				// 왼쪽에 올리기
				dfs(weights, used, N, depth + 1, left + weights[i], right);

				// 오른쪽에 올리기 (올려도 왼쪽보다 작을 때만)
				if (left >= right + weights[i]) {
					dfs(weights, used, N, depth + 1, left, right + weights[i]);
				}

				// 현재 추 사용 처리 취소하기
				used[i] = false;
			}
		}
	}
}