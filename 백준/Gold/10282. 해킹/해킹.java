import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N, D, C, INF = 10_000_000;
	static int[] minTime;
	static boolean[] visited;
	static ArrayList<Computer>[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 컴퓨터 개수
			D = Integer.parseInt(st.nextToken()); // 의존성 개수
			C = Integer.parseInt(st.nextToken()); // 해킹당한 컴퓨터의 번호

			minTime = new int[N + 1];
			visited = new boolean[N + 1];
			graph = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<Computer>();
			}

			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				graph[b].add(new Computer(a, s));
			}

			Arrays.fill(minTime, INF);
			dijkstra();
			int cnt = 0, maxTime = 0;
			for (int i = 1; i <= N; i++) {
				if (minTime[i] != INF) { // INF가 아니면 감염되는 컴퓨터
					cnt++;
					maxTime = Math.max(maxTime, minTime[i]); // 감염되기까지의 최대 시간 구하기
				}
			}
			sb.append(cnt).append(" ").append(maxTime).append("\n");
		}
		System.out.println(sb);
	}

	static void dijkstra() {
		PriorityQueue<Computer> pq = new PriorityQueue<>((c1, c2) -> Integer.compare(c1.d, c2.d));
		pq.offer(new Computer(C, 0));
		minTime[C] = 0;

		while (!pq.isEmpty()) {
			Computer now = pq.poll();
			if (visited[now.x])
				continue;
			visited[now.x] = true;

			for (Computer next : graph[now.x]) {
				if (!visited[next.x] && minTime[next.x] > minTime[now.x] + next.d) {
					minTime[next.x] = minTime[now.x] + next.d;
					pq.offer(new Computer(next.x, minTime[next.x]));
				}
			}
		}
	}

	static class Computer {
		int x, d;

		public Computer(int x, int d) {
			this.x = x;
			this.d = d;
		}
	}
}