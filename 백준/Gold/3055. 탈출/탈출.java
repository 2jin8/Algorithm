import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int R, C;
	static char[][] map;
	static boolean[][] waterVisited, dochiVisited; // 물 전용 방문 체크 배열
	static Queue<Point> water = new ArrayDeque<>();
	static Queue<Point> dochi = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		waterVisited = new boolean[R][C];
		dochiVisited = new boolean[R][C];

		Point goal = null;
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == 'D') { // 비버의 굴
					goal = new Point(i, j);
				} else if (map[i][j] == 'S') { // 고슴도치
					dochi.offer(new Point(i, j));
					dochiVisited[i][j] = true;
				} else if (map[i][j] == '*') { // 물
					water.offer(new Point(i, j));
					waterVisited[i][j] = true;
				}
			}
		}
		int result = dfs(goal);
		System.out.println(result == -1 ? "KAKTUS" : result);
	}

	static int dfs(Point goal) {
		int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };
		
		int time = 0;
		while (!dochi.isEmpty()) { // 고슴도치 큐가 비면 -1 출력
			// 물 이동
			int size = water.size();
			for (int i = 0; i < size; i++) {
				Point now = water.poll();

				for (int j = 0; j < 4; j++) {
					int nx = now.x + dx[j];
					int ny = now.y + dy[j];
					// 범위 벗어나거나 이미 방문
					if (nx < 0 || ny < 0 || nx >= R || ny >= C || waterVisited[nx][ny])
						continue;

					// 돌이 아니거나 비버 굴이 아니면 이동 가능
					if (map[nx][ny] != 'X' && map[nx][ny] != 'D') {
						water.offer(new Point(nx, ny));
						waterVisited[nx][ny] = true;
						map[nx][ny] = '*';
					}
				}
			}

			size = dochi.size();
			for (int i = 0; i < size; i++) {
				Point now = dochi.poll();
				if (now.x == goal.x && now.y == goal.y) { // 비버의 굴에 도착
					return time;
				}

				for (int j = 0; j < 4; j++) {
					int nx = now.x + dx[j];
					int ny = now.y + dy[j];
					// 범위 벗어나거나 이미 방문한 경우
					if (nx < 0 || ny < 0 || nx >= R || ny >= C || dochiVisited[nx][ny])
						continue;

					// 돌이 아니고 물이 없는 경우
					if (map[nx][ny] != 'X' && map[nx][ny] != '*') {
						dochi.offer(new Point(nx, ny));
						dochiVisited[nx][ny] = true;
					}
				}
			}
			time++;
		}
		return -1;
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}