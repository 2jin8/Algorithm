import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	static final int N = 9;
	static int[][] sudoku;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sudoku = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					sudoku[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			boolean result = checkRow() && checkCol() && checkSquare();
			sb.append("#").append(t).append(" ").append(result ? 1 : 0).append("\n");
		}
		System.out.print(sb.toString());
	}

	// 가로 확인
	public static boolean checkRow() {
		for (int i = 0; i < N; i++) {
			boolean[] used = new boolean[N + 1];
			for (int j = 0; j < N; j++) {
				int num = sudoku[i][j];
				if (used[num])
					return false;
				used[num] = true;
			}
		}
		return true;
	}

	// 세로 확인
	public static boolean checkCol() {
		for (int j = 0; j < N; j++) {
			boolean[] used = new boolean[N + 1];
			for (int i = 0; i < N; i++) {
				int num = sudoku[i][j];
				if (used[num])
					return false;
				used[num] = true;
			}
		}
		return true;
	}

	// 사각형 확인
	public static boolean checkSquare() {
		for (int i = 0; i < N; i += 3) {
			for (int j = 0; j < N; j += 3) {
				boolean[] used = new boolean[N + 1];
				for (int x = i; x < i + 3; x++) {
					for (int y = j; y < j + 3; y++) {
						int num = sudoku[x][y];
						if (used[num])
							return false;
						used[num] = true;
					}
				}
			}
		}
		return true;
	}
}