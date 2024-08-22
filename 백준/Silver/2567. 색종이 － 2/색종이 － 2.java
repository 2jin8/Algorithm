import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N = 100, W = 10;
	static int[][] arr = new int[N][N];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			for (int r = row, rEnd = row + W; r < rEnd; r++) {
				for (int c = col, cEnd = col + W; c < cEnd; c++) {
					arr[r][c] = 1;
				}
			}

		}

		int area = 0;
		int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 0)
					continue;

				// 네 방향 탐색
				for (int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					// 배열 범위를 벗어나거나 색종이로 덮인 부분이 아니면 둘레로 판단
					if (nx < 0 || ny < 0 || nx >= N || ny >= N || arr[nx][ny] == 0) {
						area++;
					}
				}
			}
		}
		System.out.println(area);
	}
}