import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, K;
	static int[][] map;
	static boolean[][][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M][K + 1]; // [x][y][부순 벽의 개수]
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		System.out.println(bfs(0, 0));
	}

	static int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };

	static int bfs(int sx, int sy) {
		Queue<Pos> queue = new ArrayDeque<>();
		queue.offer(new Pos(sx, sy, 1, 0));
		visited[sx][sy][0] = true;

		while (!queue.isEmpty()) {
			Pos now = queue.poll();
			if (now.x == N - 1 && now.y == M - 1)
				return now.d;

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny][now.crash])
					continue;

				// 벽이 아니면 이동 가능
				if (map[nx][ny] == 0) { 
					queue.offer(new Pos(nx, ny, now.d + 1, now.crash));
					visited[nx][ny][now.crash] = true;
				} 
				// 벽인 경우 & 벽을 부술 기회가 남은 경우
				else if (map[nx][ny] == 1 && now.crash < K && !visited[nx][ny][now.crash + 1]) { 
					queue.offer(new Pos(nx, ny, now.d + 1, now.crash + 1));
					visited[nx][ny][now.crash + 1] = true;
				}
			}
		}
		return -1;
	}

	static class Pos {
		int x, y, d;
		int crash; // 부순 벽의 개수

		public Pos(int x, int y, int d, int crash) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.crash = crash;
		}
	}
}