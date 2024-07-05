import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.security.auth.x500.X500Principal;
import javax.tools.Diagnostic;

public class Main {
	static int N, M, K;
	static int[][] map;
	static boolean[][][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M][K + 1];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		System.out.println(bfs());
	}

	static int bfs() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(0, 0, 0, 1, true));
		visited[0][0][0] = true;

		int[][] move = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		while (!queue.isEmpty()) {
			Node now = queue.poll();
			int x = now.x, y = now.y;
			if (x == N - 1 && y == M - 1) {
				return now.dist;
			}

			// 현재 자리에 머무르는 것이 더 짧은 경로이려면 다음날 벽을 부수려고 그러는 것? 그렇담 밤에만 자기 자리에 있도록?
			if (!now.day) {
				queue.offer(new Node(x, y, now.crash, now.dist + 1, !now.day));
				visited[x][y][now.crash] = true;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = x + move[i][0];
				int ny = y + move[i][1];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;

				// 벽이 있는 곳 & 낮인 경우 & 벽을 부술 기회가 남은 경우
				if (map[nx][ny] == 1 && now.day && now.crash < K) {
					if (!visited[nx][ny][now.crash + 1]) {
						queue.offer(new Node(nx, ny, now.crash + 1, now.dist + 1, !now.day));
						visited[nx][ny][now.crash + 1] = true;
					}
				} else if (map[nx][ny] == 0 && !visited[nx][ny][now.crash]) {
					queue.offer(new Node(nx, ny, now.crash, now.dist + 1, !now.day));
					visited[nx][ny][now.crash] = true;
				}
			}
		}

		return -1;
	}

	static class Node {
		int x, y;
		int crash; // 부순 벽의 개수
		int dist; // 이동한 칸의 개수
		boolean day; // 낮/밤 여부

		public Node(int x, int y, int crash, int dist, boolean day) {
			this.x = x;
			this.y = y;
			this.crash = crash;
			this.dist = dist;
			this.day = day;
		}
	}
}