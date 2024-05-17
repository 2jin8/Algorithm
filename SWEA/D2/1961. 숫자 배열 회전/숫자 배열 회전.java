import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	static int n;
	static int[][] arr, arr90, arr180, arr270;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			arr = new int[n][n];
			arr90 = new int[n][n];
			arr180 = new int[n][n];
			arr270 = new int[n][n];

			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			rotation(arr, arr90);
			rotation(arr90, arr180);
			rotation(arr180, arr270);
			sb.append("#").append(t).append("\n");
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					sb.append(arr90[i][j]);
				}
				sb.append(" ");
				for (int j = 0; j < n; j++) {
					sb.append(arr180[i][j]);
				}
				sb.append(" ");
				for (int j = 0; j < n; j++) {
					sb.append(arr270[i][j]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	public static void rotation(int[][] arr1, int[][] arr2) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr2[j][n - i - 1] = arr1[i][j];
			}
		}
	}
}