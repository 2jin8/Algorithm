import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {
	static int N, M, maxLen;
	static int[][] graph;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer stringTokenizer = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(stringTokenizer.nextToken());
			M = Integer.parseInt(stringTokenizer.nextToken());

			graph = new int[N + 1][N + 1];

			for (int i = 0; i < M; i++) {
				stringTokenizer = new StringTokenizer(br.readLine(), " ");
				int u = Integer.parseInt(stringTokenizer.nextToken());
				int v = Integer.parseInt(stringTokenizer.nextToken());
				graph[u][v] = 1;
				graph[v][u] = 1;
			}
			maxLen = 0;
			for (int i = 1; i <= N; i++) { // 각 노드에서 DFS 탐색
				visited = new boolean[N + 1];
				dfs(i, 1);
			}
			sb.append("#").append(t).append(" ").append(maxLen).append("\n");
		}
		System.out.println(sb.toString());
	}

	static void dfs(int node, int len) {
		visited[node] = true;

		for (int i = 1; i <= N; i++) {
			if (!visited[i] && graph[node][i] == 1) {
				dfs(i, len + 1);
				visited[i] = false;
			}
		}
		maxLen = Math.max(maxLen, len);
	}
}