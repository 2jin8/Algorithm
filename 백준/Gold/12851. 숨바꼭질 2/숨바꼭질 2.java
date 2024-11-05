import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final int MAX = 100_001;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		visited = new boolean[MAX];
		if (N == K) {
			System.out.println(0);
			System.out.println(1);
		} else {
			bfs(N, K);
		}
	}

	static void bfs(int N, int K) {
		Queue<Integer> queue = new ArrayDeque<>();
		visited[N] = true;
		queue.offer(N);

		int cnt = 0, minTime = 0;
		HashSet<Integer> visitedList = new HashSet<>();
		while (!queue.isEmpty()) {
			minTime++;
			visitedList.clear();
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int now = queue.poll();

				int next = now - 1;
				if (next == K) cnt++;
				else if (next >= 0 && !visited[next]) {
					queue.offer(next);
					visitedList.add(next);
				}

				next = now + 1;
				if (next == K) cnt++;
				else if (next < MAX && !visited[next]) {
					queue.offer(next);
					visitedList.add(next);
				}

				next = now * 2;
				if (next == K) cnt++;
				else if (next < MAX && !visited[next]) {
					queue.offer(next);
					visitedList.add(next);
				}
			}
			
			// 최단 거리를 찾으면 종료하기
			if (cnt != 0) break;
			
			// 한 레벨에서 만들 수 있는 모든 수를 큐에 넣은 후 방문 처리하기
			for (int v : visitedList) {
				visited[v] = true;
			}
		}
		System.out.println(minTime);
		System.out.println(cnt);
	}
}