import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	static int V, E, MAX = 999_999_999;
	static boolean[] visited; // 방문 확인용
	static ArrayList<Vertex>[] graph; // 정점 연결 정보
	static PriorityQueue<Vertex> pq; // 최소 가중치를 찾기 위한 우선순위 큐

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			// 초기화
			visited = new boolean[V + 1];
			graph = new ArrayList[V + 1];
			for (int i = 1; i <= V; i++) {
				graph[i] = new ArrayList<>();
			}
			pq = new PriorityQueue<>();

			// 정점의 정보 저장 (양방향)
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				graph[a].add(new Vertex(b, c));
				graph[b].add(new Vertex(a, c));
			}

			// 정점 정보 저장
			pq.offer(new Vertex(1, 0)); // 1번 정점에서 시작
			for (int i = 2; i <= V; i++) {
				pq.offer(new Vertex(i, MAX));
			}

			long cost = 0; // cost: 최소 스패닝 트리의 가중치
			for (int i = 0; i < V; i++) { // 정점의 수만큼 반복
				// 최소 가중치를 가지는 노드 빼기
				
				Vertex vertex = null;
				while (true) {
					vertex = pq.poll();
					if (!visited[vertex.idx]) break; 
				}
				
				int min = vertex.weight, minIdx = vertex.idx;

				// 방문 처리
				visited[minIdx] = true;
				cost += min;

				// 가중치 업데이트
				for (Vertex v : graph[minIdx]) {
					// 방문하지 않은 노드 우선순위 큐에 넣기
					if (!visited[v.idx]) {
						pq.offer(v); // 우선순위 큐에 넣으면 자동 정렬 > 항상 가중치가 작은 것이 먼저 나옴
					}
				}
			}
			sb.append("#").append(t).append(" ").append(cost).append("\n");
		}
		System.out.println(sb);
	}

	static class Vertex implements Comparable<Vertex> {
		int idx, weight;

		public Vertex(int idx, int weight) {
			this.idx = idx;
			this.weight = weight;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.weight - o.weight; // 가중치 기준 오름차순 정렬
		}
	}
}