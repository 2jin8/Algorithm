import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int N, M;
	static boolean[] visited;
	static int[] count; // 연결된 정점의 수를 저장하는 배열
	static ArrayList<Integer>[] originList, reverseList; // 순방향/역방향 그래프

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());

			originList = new ArrayList[N + 1];
			reverseList = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++) {
				originList[i] = new ArrayList<Integer>();
				reverseList[i] = new ArrayList<Integer>();
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				originList[a].add(b);
				reverseList[b].add(a);
			}

			// 각 정점에서 DFS 탐색 > 연결된 정점의 수 세기
			visited = new boolean[N + 1];
			count = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				Arrays.fill(visited, false);
				dfs(originList, i);
				Arrays.fill(visited, false);
				dfs(reverseList, i);
			}

			int ans = 0;
			for (int cnt : count) {
				// 모든 정점과 연결되어 있다면 순서를 알 수 있음
				if (cnt == N - 1)
					ans++;
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	// 단순 DFS 탐색
	static void dfs(ArrayList<Integer>[] list, int x) {
		visited[x] = true;

		for (int y : list[x]) {
			if (visited[y])
				continue;

			// 연결된 정점의 수 1 증가
			count[y]++;
			dfs(list, y);
		}
	}
}