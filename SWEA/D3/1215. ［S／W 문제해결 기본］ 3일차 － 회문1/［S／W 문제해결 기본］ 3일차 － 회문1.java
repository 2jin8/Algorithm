import java.util.Scanner;

class Solution
{
	static final int N = 8;
	static char[][] board = new char[N][N];
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T=10;

		for(int test_case = 1; test_case <= T; test_case++)
		{
			sb.append("#").append(test_case).append(" ");
			int len = Integer.parseInt(sc.nextLine()); // 찾아야 하는 회문의 길이
			for (int i=0; i<N; i++) {
				String str = sc.nextLine();
				for (int j=0; j<N; j++) {
					char c = str.charAt(j);
					board[i][j] = c;
				}
			}
			
			// 행 검사
			boolean isEqual;
			int total = 0;
			for (int i=0; i<N; i++) {
				for (int j=0; j<=N-len; j++) { // j가 탐색 시작 위치
					int start = j, end = j + len - 1;
					isEqual = true; // 탐색 전 초기화 필수!
					while (start <= end) {
						if (board[i][start] != board[i][end]) {
							isEqual = false;
							break;
						}
						start++; end--;
					}
					if (isEqual) total++;
				}
			}
			
			// 열 검사
			for (int i=0; i<N; i++) {
				for (int j=0; j<=N-len; j++) {
					int start = j, end = j + len - 1;
					isEqual = true;
					while (start <= end) {
						if (board[start][i] != board[end][i]) {
							isEqual = false;
							break;
						}
						start++; end--;
					}
					if (isEqual) total++;
				}
			}
			sb.append(total).append("\n");
		}
		System.out.println(sb.toString());
	}
}