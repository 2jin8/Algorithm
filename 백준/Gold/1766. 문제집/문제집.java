import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] degree;
	static boolean[] solved;
	static ArrayList<Integer>[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		degree = new int[N + 1];
		solved = new boolean[N + 1];
		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) { // a번 문제는 b번 문제보다 먼저 푸는 것이 좋음
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			degree[b]++; // 진입차수 증가
			graph[a].add(b);
		}

		Queue<Integer> queue = new ArrayDeque<>();
		PriorityQueue<Problem> pq = new PriorityQueue<>((p1, p2) -> Integer.compare(p1.num, p2.num));
		for (int i = 1; i <= N; i++) {
			// 진입 차수가 0인 문제 번호 넣기
			if (degree[i] == 0) {
				queue.offer(i);
				pq.offer(new Problem(0, i));
			}
		}

		solved[0] = true;
		StringBuilder sb = new StringBuilder();
		while (!queue.isEmpty() && !pq.isEmpty()) {
			// 이미 뺀 것은 넘어가기
			int p1 = queue.peek();
			while (!queue.isEmpty() && solved[p1]) {
				queue.poll();
				p1 = queue.peek();
			}
			Problem p2 = pq.peek();
			while (!pq.isEmpty() && solved[p2.num]) {
				pq.poll();
				p2 = pq.peek();
			}
			
			int prev;
			// 큐의 첫 번째 값보다 우선순위 큐의 값이 쉬운 문제 & 이전에 풀어야 하는 문제를 이미 다 푼 경우
			if (p1 >= p2.num && solved[p2.prev]) {
				pq.poll();
				prev = p2.num;
			} 
			// 그렇지 않은 경우
			else {
				queue.poll();
				prev = p1;
			}
			
			solved[prev] = true;
			sb.append(prev).append(" ");
			for (int next : graph[prev]) {
				if (--degree[next] == 0) { // 진입차수 1개씩 줄이기 & 0이되면 큐에 넣기
					queue.offer(next);
					pq.offer(new Problem(p2.num, next));
				}
			}
		}
		System.out.println(sb);
	}
	
	static class Problem {
		int prev, num;
		
		public Problem(int prev, int num) {
			this.prev = prev;
			this.num = num;
		}
	}
}