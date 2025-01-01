import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N = 5;
	static boolean[][] bingo;
	static Pos[] pos;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		pos = new Pos[N * N + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				pos[num] = new Pos(i, j);
			}
		}

		boolean complete = false;
		bingo = new boolean[N][N];
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			if (complete) continue;
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				Pos p = pos[num];
				bingo[p.x][p.y] = true;
				cnt++;

				if (isBingo()) {
					complete = true;
					break;
				}
			}
		}
		System.out.println(cnt);
	}

	static boolean isBingo() {
		int cnt = 0;
		// 가로, 세로 확인하기
		for (int i = 0; i < N; i++) {
			int row = 0, col = 0;
			for (int j = 0; j < N; j++) {
				// 수가 지워졌으면 ++
				if (bingo[i][j]) row++;

				if (bingo[j][i]) col++;
			}

			if (row == 5) cnt++;
			if (col == 5) cnt++;
		}

		// 가로, 세로로 3줄이 그어졌으면 true
		if (cnt >= 3) return true;

		// 대각선 확인하기
		int left = 0, right = 0;
		for (int i = 0; i < N; i++) {
			// 수가 지워졌으면 ++
			if (bingo[i][i]) left++;
			if (bingo[i][N - 1 - i]) right++;
		}

		if (left == 5) cnt++;
		if (right == 5) cnt++;

		return cnt >= 3;
	}

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
