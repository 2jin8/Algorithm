import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final int N = 102, W = 10;
	static int[][] arr = new int[N][N];
	static boolean[][] visited = new boolean[N][N];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine()); // 색종이의 수
		
		// 색종이의 위치 기록하기
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken()) + 1;
			int col = Integer.parseInt(st.nextToken()) + 1;
			for (int r = row, rEnd = row + W; r < rEnd; r++) {
				for (int c = col, cEnd = col + W; c < cEnd; c++) {
					arr[r][c] = 1;
				}
			}
		}

		int area = 0;
		// 색종이가 붙여지지 않은 영역 BFS 탐색
		// 1. 색종이가 붙여지지 않은 지역에서 네 방향 탐색하기
		// 2. 이동하려는 곳이 색종이가 붙여진 곳 => 색종이의 둘레에 해당함
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 0 && !visited[i][j]) {
					area += bfs(i, j);
				}
			}
		}
		System.out.println(area);
	}

	static int bfs(int x, int y) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { x, y });
		visited[x][y] = true;

		int area = 0;
		int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };
		while (!queue.isEmpty()) {
			int[] now = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny])
					continue;

				// 색종이가 붙여진 지역이면 둘레에 포함되므로 세기
				if (arr[nx][ny] == 1) {
					area++;
				} else { // 색종이가 붙여지지 않은 지역이면 큐에 넣기
					queue.offer(new int[] { nx, ny });
					visited[nx][ny] = true;
				}
			}
		}
		return area;
	}
}