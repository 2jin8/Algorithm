import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int M, N, tomato; // M: 가로, N: 세로
	static int[][] arr;
	static boolean[][] visited;
	static Queue<Point> queue = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 0) {
					tomato++;
				} else if (arr[i][j] == 1) {
					queue.offer(new Point(i, j));
					visited[i][j] = true;
				}
			}
		}
		// 익지 않은 토마토가 없으면 0일 소요
		if (tomato == 0)
			System.out.println(0);
		else
			System.out.println(bfs());
	}

	static int bfs() {
		int day = 0, change = 0;
		int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };
		while (!queue.isEmpty()) {
			day++;
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Point now = queue.poll();

				for (int j = 0; j < 4; j++) {
					int nx = now.x + dx[j];
					int ny = now.y + dy[j];

					// 범위를 벗어난 경우
					if (nx < 0 || ny < 0 || nx >= N || ny >= M)
						continue;

					if (!visited[nx][ny] && arr[nx][ny] == 0) {
						queue.offer(new Point(nx, ny));
						visited[nx][ny] = true;
						change++; // 익게 되는 토마토의 수 세기
					}
				}
			}
		}
		return tomato == change ? day - 1 : -1; // 0일차도 셌으므로 1을 빼야 함
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}