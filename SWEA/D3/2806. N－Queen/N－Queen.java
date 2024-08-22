import java.io.BufferedReader;
import java.io.InputStreamReader;

// 같은 행에는 퀸을 놓지 않음 > 각 행의 열 정보만 저장하면 됨!
public class Solution {

	static int N, answer;
	static int[] col; // 열 정보를 저장하기 위한 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			col = new int[N + 1];
			answer = 0;
			setQueen(1);
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

	static void setQueen(int r) {
		// 이전 위치에 놓은 것까지 유효한지 확인하기
		if (!checkPosition(r - 1))
			return;

		// 모든 행에 퀸을 다 놓았으면 정답 & 탐색 끝!
		if (r > N) {
			answer++;
			return;
		}

		// [r][1] ~ [r][N]에 놓기
		for (int c = 1; c <= N; c++) {
			col[r] = c;
			setQueen(r + 1);
		}
	}

	static boolean checkPosition(int row) {
		// 같은 행에는 놓지 않았으므로 열과 대각선 확인하기
		for (int r = 1; r < row; r++) {
			// 같은 열에 놓는 것 or 대각선에 놓는 것이면 false 반환하기 (대각선과는 row, col 차이가 각각 1)
			if (col[r] == col[row] || row - r == Math.abs(col[row] - col[r])) {
				return false;
			}
		}
		return true;
	}
}