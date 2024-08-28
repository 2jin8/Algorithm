import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static final int N = 101;
	static int[] dist = new int[N];
	static ArrayList<Integer>[] graph = new ArrayList[N];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= 10; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());
			for (int i = 1; i < N; i++) {
				graph[i] = new ArrayList<Integer>();
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				if (!graph[from].contains(to)) {
					graph[from].add(to);
				}
			}
			Arrays.fill(dist, 0);
			sb.append("#").append(t).append(" ").append(bfs(S)).append("\n");
		}
		System.out.println(sb);
	}

	static int bfs(int S) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(S);
		dist[S] = 1;

		int maxValue = 0; // 마지막에 연락 받은 사람을 찾기 위한 변수
		while (!queue.isEmpty()) {
			int now = queue.poll();
			if (dist[now] > maxValue)
				maxValue = dist[now];
			for (int next : graph[now]) {
				if (dist[next] == 0) {
					dist[next] = dist[now] + 1;
					queue.offer(next);
				}
			}
		}

		// 배열의 값이 maxValue랑 같고 값이 큰 것 찾기
		int maxNum = 0;
		for (int i = 1; i < N; i++) {
			if (dist[i] == maxValue) {
				maxNum = i;
			}
		}
		return maxNum;
	}
}
