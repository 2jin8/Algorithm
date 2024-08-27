import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 
		int M = Integer.parseInt(st.nextToken());
		int[] degree = new int[N + 1];
		ArrayList<Integer>[] graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			degree[b]++;
		}

		// 진입 차수가 0인 학생 큐에 넣기
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if (degree[i] == 0) {
				queue.offer(i);
			}
		}

		ArrayList<Integer> ans = new ArrayList<>();
		while (!queue.isEmpty()) {
			int now = queue.poll();
			ans.add(now);

			for (int next : graph[now]) {
				// 현재 학생과 연결된 모든 학생의 연결 끊기
				if (--degree[next] == 0) {
					queue.offer(next);
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int a : ans) {
			sb.append(a).append(" ");
		}
		System.out.println(sb);
	}
}