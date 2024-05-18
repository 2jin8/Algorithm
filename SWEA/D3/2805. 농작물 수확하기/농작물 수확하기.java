import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine());
			int[][] map = new int[n][n];
			for (int i = 0; i < n; i++) {
				String[] strings = br.readLine().split(""); 
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(strings[j]);
				}
			}

			int l = n / 2, r = n / 2, i = 0;
			int total = 0;
			// 상단부
			while (l > 0 && r < n - 1) {
				for (int j = l; j <= r; j++) {
					total += map[i][j];
				}
				i++;
				l--;
				r++;
			}
			
			// 하단부
			while (l <= r) {
				for (int j = l; j <= r; j++) {
					total += map[i][j];
				}
				i++;
				l++;
				r--;
			}
			sb.append('#').append(t).append(' ').append(total).append('\n');
		}
		System.out.println(sb.toString());
	}
}