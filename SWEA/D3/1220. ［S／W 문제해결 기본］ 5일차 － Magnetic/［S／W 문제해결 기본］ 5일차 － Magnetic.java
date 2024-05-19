import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {
	static int n, deadLockCnt;
	static int[][] board;
	static Queue<int[]> queue = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= 10; t++) {
			n = Integer.parseInt(br.readLine());
			board = new int[n][n];
			for (int i = 0; i < n; i++) {
				StringTokenizer stringTokenizer = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < n; j++) {
					board[i][j] = Integer.parseInt(stringTokenizer.nextToken());
					if (board[i][j] == 1)
						queue.offer(new int[] { i, j });
				}
			}
			deadLockCnt = 0;
			findDeadLock();

			sb.append("#").append(t).append(" ").append(deadLockCnt).append("\n");
		}
		System.out.println(sb.toString());
	}

	static void findDeadLock() {
		while (!queue.isEmpty()) {
			int[] pos = queue.poll();
			int x = pos[0], y = pos[1];
			for (int i = x + 1; i < n; i++) {
				// 같은 자성체를 만나면 다음 자성체에 교착상태 확인을 맡기고 탐색 종료
				if (board[i][y] == 1) { 
					break; 
				}
				// 다른 자성체를 만나면 교착상태가 발생하므로 탐색 종료
				if (board[i][y] == 2) { 
					deadLockCnt++;
					break;
				}
			}
		}
	}
}