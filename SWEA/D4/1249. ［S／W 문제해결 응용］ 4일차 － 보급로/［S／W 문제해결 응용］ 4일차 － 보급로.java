import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// BFS + PQ
public class Solution {
	static int N;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			arr = new int[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				char[] chars = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					arr[i][j] = chars[j] - '0';
				}
			}
			sb.append("#").append(t).append(" ").append(bfs(0, 0, N - 1, N - 1)).append("\n");
		}
		System.out.println(sb);
	}

	static int bfs(int sx, int sy, int ex, int ey) {
		// 시간 기준 내림차순 정렬
		PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.time, n2.time));
		pq.offer(new Node(0, 0, 0));
		visited[0][0] = true;

		while (!pq.isEmpty()) {
			Node now = pq.poll(); // 시간이 가장 짧은게 나옴
			int x = now.x, y = now.y;
			if (x == N - 1 && y == N - 1)
				return now.time;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny])
					continue;

				pq.offer(new Node(nx, ny, now.time + arr[nx][ny]));
				visited[nx][ny] = true;
			}
		}
		return -1;
	}

	static class Node {
		int x, y, time;

		public Node(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
}