import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static long ans;
	static int[][] worms;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			worms = new int[N][2];
			// 지렁이 정보 저장
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				worms[i][0] = Integer.parseInt(st.nextToken());
				worms[i][1] = Integer.parseInt(st.nextToken());
			}
			visited = new boolean[N];
			ans = Long.MAX_VALUE;
			dfs(0, 0);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	// 출발할 지렁이들을 선택하는 방법은 조합
	static void dfs(int depth, int start) {
		if (depth == N / 2) { // 출발할 지렁이(N/2마리) 다 뽑은 경우
			long xSum = 0, ySum = 0;
			for (int i = 0; i < N; i++) { // 출발 지점이라면 더하고 도착 지점이라면 빼기 (벡터!)
				if (visited[i]) {
					xSum += worms[i][0];
					ySum += worms[i][1];
				} else {
					xSum -= worms[i][0];
					ySum -= worms[i][1];
				}
			}
			ans = Math.min(ans, xSum * xSum + ySum * ySum);
			return;
		}

		for (int i = start; i < N; i++) {
			visited[i] = true;
			dfs(depth + 1, i + 1);
			visited[i] = false;
		}
	}
}