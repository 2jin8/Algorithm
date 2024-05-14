import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int[][] arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int total = 0;
			for (int i = 0; i < N; i++) {
				// 가로 확인
				for (int j = 0; j < N; j++) {
					int cnt = 0; // 연속된 1의 개수
					while (j < N && arr[i][j] == 1) {
						cnt++;
						j++;
					}
					if (cnt == K)
						total++;
				}
				// 세로 확인
				for (int j = 0; j < N; j++) {
					int cnt = 0; // 연속된 1의 개수
					while (j < N && arr[j][i] == 1) {
						cnt++;
						j++;
					}
					if (cnt == K)
						total++;
				}
			}
			sb.append("#").append(t).append(" ").append(total).append("\n");
		}
		System.out.print(sb.toString());
	}
}