import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int R, C, maxCnt;
	static char[][] board;
	static boolean[] visited = new boolean[26];
	static int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				board[i][j] = line.charAt(j);
			}
		}
		// 좌측 상단에서 시작해서 최대한 몇 칸을 지날 수 있는지 구하기
		dfs(0, 0, 1);
		System.out.println(maxCnt);
	}

	static void dfs(int x, int y, int cnt) {
		int idx = board[x][y] - 'A';

		visited[idx] = true;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || ny < 0 || nx >= R || ny >= C)
				continue;

			// 해당 알파벳을 사용하지 않았을 경우에만 DFS 탐색 진행
			if (!visited[board[nx][ny] - 'A']) {
				dfs(nx, ny, cnt + 1);
			}
		}
		visited[idx] = false;

		maxCnt = Math.max(maxCnt, cnt);
	}
}