import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int L, R, C;
	static char[][][] building;
	static boolean[][][] visited;
	static int[] dx = { 1, -1, 0, 0, 0, 0 }, dy = { 0, 0, 1, -1, 0, 0 }, dz = { 0, 0, 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			if (L == 0 && R == 0 && C == 0) break;
			
			building = new char[R][C][L];
			visited = new boolean[R][C][L];
			int sx = -1, sy = -1, sz = -1;
			for (int k = 0; k < L; k++) {
				for (int i = 0; i < R; i++) {
					String line = br.readLine();
					for (int j = 0; j < C; j++) {
						building[i][j][k] = line.charAt(j);
						if (building[i][j][k] == 'S') {
							sx = i;
							sy = j;
							sz = k;
						}
					}
				}
				br.readLine();
			}
			int bfs = bfs(sx, sy, sz);
			sb.append(bfs == -1 ? "Trapped!" : ("Escaped in " + bfs + " minute(s).")).append("\n");
		}
		System.out.println(sb);
	}

	static int bfs(int sx, int sy, int sz) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(sx, sy, sz, 0));
		visited[sx][sy][sz] = true;

		while (!queue.isEmpty()) {
			Point now = queue.poll();
			if (building[now.x][now.y][now.z] == 'E')
				return now.dist;

			for (int i = 0; i < 6; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				int nz = now.z + dz[i];
				if (nx < 0 || ny < 0 || nz < 0 || nx >= R || ny >= C || nz >= L)
					continue;

				if (visited[nx][ny][nz] || building[nx][ny][nz] == '#')
					continue;

				queue.offer(new Point(nx, ny, nz, now.dist + 1));
				visited[nx][ny][nz] = true;
			}
		}
		return -1;
	}

	static class Point {
		int x, y, z, dist;

		public Point(int x, int y, int z, int dist) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.dist = dist;
		}
	}
}