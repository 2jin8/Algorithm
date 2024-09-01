import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, M;
	static char[][] map;
	static boolean[][] visited;
	static Queue<Pos> queue = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			queue.clear();
			visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = line.charAt(j);
					if (map[i][j] == 'W') { // 물의 위치 저장 & 방문 처리
						queue.offer(new Pos(i, j, 0));
						visited[i][j] = true;
					}
				}
			}
			sb.append("#").append(t).append(" ").append(bfs()).append("\n");
		}
		System.out.println(sb);
	}

	static int bfs() {
		int totalMove = 0;
		int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 }; // 상하좌우 이동
		while (!queue.isEmpty()) {
			Pos now = queue.poll();
			totalMove += now.move; // 이동 횟수 더하기

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny])
					continue;

				// 처음에 모든 물의 위치를 큐에 넣고 방문 처리 => 큐에 넣을 때 확인할 필요 X
				queue.offer(new Pos(nx, ny, now.move + 1));
				visited[nx][ny] = true;
			}
		}
		return totalMove;
	}

	static class Pos {
		int x, y, move;

		public Pos(int x, int y, int move) {
			this.x = x;
			this.y = y;
			this.move = move;
		}
	}
}