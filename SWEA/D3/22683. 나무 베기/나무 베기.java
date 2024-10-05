import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	static int N, K;
	static char[][] map;
	static boolean[][][][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new char[N][N];
			visited = new boolean[N][N][4][K + 1]; // [x][y][방향][나무제거]
			Point start = null, end = null;
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j);
					if (map[i][j] == 'X') { // 시작 위치
						start = new Point(i, j, 0, 0, 0);
					} else if (map[i][j] == 'Y') { // 종료 위치
						end = new Point(i, j, 0, 0, 0);
					}
				}
			}
			sb.append("#").append(t).append(" ").append(bfs(start, end)).append("\n");
		}
		System.out.println(sb);
	}

	static int[] dx = { -1, 0, 1, 0 }, dy = { 0, 1, 0, -1 };

	static int bfs(Point start, Point end) {
		PriorityQueue<Point> pq = new PriorityQueue<>((p1, p2) -> Integer.compare(p1.remote, p2.remote));
		pq.offer(start);
		visited[start.x][start.y][start.d][start.cut] = true;

		while (!pq.isEmpty()) {
			Point now = pq.poll();
			if (now.x == end.x && now.y == end.y)
				return now.remote;

			// 왼쪽으로 방향 변경
			int nd = now.d - 1;
			if (nd < 0)
				nd = 3;
			if (!visited[now.x][now.y][nd][now.cut]) {
				visited[now.x][now.y][nd][now.cut] = true;
				pq.offer(new Point(now.x, now.y, nd, now.cut, now.remote + 1));
			}

			// 오른쪽으로 방향 변경
			nd = now.d + 1;
			if (nd > 3)
				nd = 0;
			if (!visited[now.x][now.y][nd][now.cut]) {
				visited[now.x][now.y][nd][now.cut] = true;
				pq.offer(new Point(now.x, now.y, nd, now.cut, now.remote + 1));
			}

			// 앞으로 전진
			// 현재 위치에 나무가 있고 나무 제거 횟수가 남았다면 나무 제거 후 이동
			int nx = now.x + dx[now.d];
			int ny = now.y + dy[now.d];
			if (nx < 0 || ny < 0 || nx >= N || ny >= N)
				continue;

			// 현재 위치에 나무가 있고 나무 제거 횟수가 남았다면 나무를 제거해야 함(그래야 이동 가능)
			if (map[now.x][now.y] == 'T' && now.cut < K) {
				if (!visited[nx][ny][now.d][now.cut + 1]) {
					visited[nx][ny][now.d][now.cut + 1] = true;
					pq.offer(new Point(nx, ny, now.d, now.cut + 1, now.remote + 1));
				}
			} 
			// 현재 위치에 나무가 없으면 그냥 이동 가능
			else if (map[now.x][now.y] != 'T') {
				if (!visited[nx][ny][now.d][now.cut]) {
					visited[nx][ny][now.d][now.cut] = true;
					pq.offer(new Point(nx, ny, now.d, now.cut, now.remote + 1));
				}
			}
		}
		return -1;
	}

	static class Point {
		int x, y, d;
		int cut, remote;

		public Point(int x, int y, int d, int cut, int remote) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.cut = cut;
			this.remote = remote;
		}
	}
}