import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N, total;
	static int[] weights, nums; // weights: 각 무게추의 무게를 저장하는 배열, nums: 순열을 저장하는 배열
	static boolean[] used; // 무게추 사용 여부를 저장하는 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			weights = new int[N];
			nums = new int[N];
			used = new boolean[N];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				weights[i] = Integer.parseInt(st.nextToken());
			}

			total = 0;
			dfs(0);
			sb.append("#").append(t).append(" ").append(total).append("\n");
		}
		System.out.println(sb);
	}

    // 순열 만들기
	private static void dfs(int depth) {
		// 모든 추를 다 사용한 경우
		if (depth == N) {
			leftOrRight(0, 0, 0);
			return;
		}

		// 모든 무게 추를 양팔저울 위에 올리는 순서
		for (int i = 0; i < N; i++) {
			if (!used[i]) {

				used[i] = true; // 현재 추 사용 처리하기
				nums[depth] = weights[i]; // 현재 값 기록하기
				dfs(depth + 1);
				used[i] = false; // 현재 추 사용 처리 취소하기
			}
		}
	}

    // 왼쪽 저울 또는 오른쪽 저울에 올리기
	static void leftOrRight(int depth, int left, int right) {
		if (depth == N) {
			total++;
			return;
		}

		// 왼쪽 저울에 넣기
		leftOrRight(depth + 1, left + nums[depth], right);

		// 오른쪽 저울에 넣기
		if (left >= right + nums[depth]) {
			leftOrRight(depth + 1, left, right + nums[depth]);
		}
	}
}