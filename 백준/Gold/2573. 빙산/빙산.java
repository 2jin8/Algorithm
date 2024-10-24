import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] arr;
	static Queue<int[]> queue = new ArrayDeque<>();
	static int[] dx = { 1, 0, -1, 0 }, dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] != 0) {
					queue.offer(new int[] { i, j });
				}
			}
		}
		System.out.println(bfs());
	}

	static int bfs() {
		int year = 0;
		while (!queue.isEmpty()) {
			// 덩어리가 분리되었으면 return year
			int[] peek = queue.peek();
			if (!checkSeperate(peek[0], peek[1]))
				return year;

			year++;
			int size = queue.size();
			int[][] tmp = copyArray(arr);
			for (int s = 0; s < size; s++) {
				int[] now = queue.poll();
				int x = now[0], y = now[1];

				// 네 방향 탐색 - 바다의 개수 세기
				int sea = 0;
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (nx < 0 || ny < 0 || nx >= N || ny >= M)
						continue;

					if (arr[nx][ny] == 0)
						sea++;
				}

				tmp[x][y] = Math.max(0, arr[x][y] - sea);
				if (tmp[x][y] != 0) { // 아직 빙산이 남아있다면 큐에 넣기
					queue.offer(new int[] { x, y });
				}
			}
			// 변경된 정보 복사하기
			arr = copyArray(tmp);
		}
		return 0;
	}

	static boolean checkSeperate(int x, int y) {
		int area = 0;
		Queue<int[]> tmpQ = new ArrayDeque<>();
		tmpQ.offer(new int[] { x, y });
		boolean[][] visited = new boolean[N][M];
		visited[x][y] = true;

		while (!tmpQ.isEmpty()) {
			int[] now = tmpQ.poll();
			area++;

			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;

				if (arr[nx][ny] == 0 || visited[nx][ny])
					continue;

				tmpQ.offer(new int[] { nx, ny });
				visited[nx][ny] = true;
			}
		}
		// 모든 빙산이 연결되어 있다면 큐에 저장된 크기와 같음
		return area == queue.size();
	}

	static int[][] copyArray(int[][] arr) {
		int[][] tmp = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmp[i][j] = arr[i][j];
			}
		}
		return tmp;
	}
}