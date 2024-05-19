import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
	static int len, palindromeCnt;
	static final int N = 8;
	static char[][] board;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= 10; t++) {
			len = Integer.parseInt(br.readLine()); // 찾아야 하는 회문의 길이
			board = new char[N][N];
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					board[i][j] = line.charAt(j);
				}
			}

			palindromeCnt = 0; // 회문의 개수
			findPalindrome();
			sb.append("#").append(t).append(" ").append(palindromeCnt).append("\n");
		}
		System.out.println(sb.toString());
	}

	static void findPalindrome() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N - len; j++) {
				// Row 확인
				StringBuilder builder = new StringBuilder();
				for (int k = j; k < j + len; k++) {
					builder.append(board[i][k]);
				}
				String origin = builder.toString();
				String reverse = builder.reverse().toString();
				if (origin.equals(reverse))
					palindromeCnt++;

				// Col 확인
				builder.setLength(0);
				for (int k = j; k < j + len; k++) {
					builder.append(board[k][i]);
				}
				origin = builder.toString();
				reverse = builder.reverse().toString();
				if (origin.equals(reverse))
					palindromeCnt++;

			}
		}
	}
}