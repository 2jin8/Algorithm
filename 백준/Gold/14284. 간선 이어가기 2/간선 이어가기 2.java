import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static final int INF = 1_000_000_000;
	static int N, M, S, T;
	static ArrayList<Node>[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<Node>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[a].add(new Node(b, c));
			graph[b].add(new Node(a, c));
		}

		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		System.out.println(dijkstra());
	}
	
	static int dijkstra() {
		// 거리 기준 오름차순 정렬
		PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.d, n2.d));
		int[] minDist = new int[N + 1];
		Arrays.fill(minDist, INF);
		pq.offer(new Node(S, 0));
		minDist[S] = 0;
		
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			
			// 현재까지의 최소 가중치 합이 기록된 값보다 크다면 탐색할 필요 X
			if (now.d > minDist[now.x]) continue;
			// T에 도착했다면 탐색 종료
			if (now.x == T)
				break;
			
			for (Node next : graph[now.x]) {
				// now를 거쳐서 가는게 더 짧다면 갱신 & 큐에 넣기
				if (minDist[next.x] > now.d + next.d) {
					minDist[next.x] = now.d + next.d;
					pq.offer(new Node(next.x, minDist[next.x]));
				}
			}
		}
		return minDist[T];
	}

	static class Node {
		int x, d;

		public Node(int x, int d) {
			this.x = x;
			this.d = d;
		}
	}
}