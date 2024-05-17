import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	static int n, m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			int[] A = new int[n];
			int[] B = new int[m];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < n; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < m; i++) {
				B[i] = Integer.parseInt(st.nextToken());
			}
			

			int max = Integer.MIN_VALUE;
			if (n == m) {
				max = 0;
				for (int i = 0; i < n; i++) {
					max += A[i] * B[i];
				}
			} else if (n < m) {
				for (int i = 0; i <= m - n; i++) {
					int idx = 0, total = 0;
					for (int j = i; j < i + n; j++) {
						total += A[idx++] * B[j];
					}
					max = Math.max(max, total);
				}
			} else {
				for (int i = 0; i <= n - m; i++) {
					int idx = 0, total = 0;
					for (int j = i; j < i + m; j++) {
						total += A[j] * B[idx++];
					}
					max = Math.max(max, total);
				}
			}
			sb.append("#").append(t).append(" ").append(max).append("\n");
		}
		System.out.println(sb.toString());
	}
}