import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, minCost;
	static int[] arr[], nums;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		minCost = Integer.MAX_VALUE;
		nums = new int[N + 1];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) { // 시작 정점
			nums[0] = nums[N] = i;
			visited[i] = true;
			dfs(1, 0);
			visited[i] = false;
		}
		System.out.println(minCost);
	}

	static void dfs(int depth, int cost) {
		// 최소 비용보다 크다면  정답 X
		if (cost >= minCost)
			return;

		if (depth == N) {
			// 연결되어있는지 확인
			int c = arr[nums[depth - 1]][nums[depth]];
			if (c > 0 && minCost > cost + c) {
				minCost = cost + c;
			}
			return;
		}

		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;

			nums[depth] = i;
			visited[i] = true;
			int c = arr[nums[depth - 1]][nums[depth]];
			if (c > 0) // 연결되어 있어야 이동 가능
				dfs(depth + 1, cost + c);
			visited[i] = false;
		}
	}

	static class Pos {
		int x, w;

		public Pos(int x, int w) {
			this.x = x;
			this.w = w;
		}
	}
}