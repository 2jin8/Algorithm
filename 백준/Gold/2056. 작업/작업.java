import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] times, indegree;
	static ArrayList<Integer>[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		times = new int[N + 1];
		indegree = new int[N + 1];
		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		StringTokenizer st = null;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			times[i] = Integer.parseInt(st.nextToken()); // 걸리는 시간
			int M = Integer.parseInt(st.nextToken());
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());
				graph[i].add(num);
				indegree[num]++; // 진입차수 증가
			}
		}

		PriorityQueue<Work> pq = new PriorityQueue<>((w1, w2) -> Integer.compare(w1.totalTime, w2.totalTime));
		boolean[] used = new boolean[N + 1];
		// 진입차수가 0인 작업 넣기
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0)
				pq.offer(new Work(i, times[i]));
		}

		Work now = null;
		while (!pq.isEmpty()) {
			now = pq.poll();
			used[now.x] = true;

			for (int next : graph[now.x]) {
				if (used[next])
					continue;

				if (--indegree[next] == 0) {
					pq.offer(new Work(next, now.totalTime + times[next]));
				}
			}
		}
		System.out.println(now.totalTime);
	}

	static class Work {
		int x, totalTime;

		public Work(int x, int totalTime) {
			this.x = x;
			this.totalTime = totalTime;
		}
	}
}