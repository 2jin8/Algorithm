import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution {
	static int N, M;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
			for (int i = 0; i <= N - M; i++) {
				for (int j = 0; j <= N - M; j++) {
					pq.offer(getSum(i, j));
				}
			}
			sb.append("#").append(t).append(" ").append(pq.poll()).append("\n");
		}
		System.out.println(sb.toString());
	}

	static int getSum(int x, int y) {
		int total = 0;
		for (int i = x; i < x + M; i++) {
			for (int j = y; j < y + M; j++) {
				total += arr[i][j];
			}
		}
		return total;
	}
}
