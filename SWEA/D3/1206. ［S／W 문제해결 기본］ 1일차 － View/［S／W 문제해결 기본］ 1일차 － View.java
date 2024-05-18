import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.StringTokenizer;

class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= 10; t++) {
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int[] height = new int[n];
			for (int i = 0; i < n; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}

			int total = 0;
			for (int i = 2; i < n - 2; i++) {
				int h = height[i];
				if (h > height[i - 1] && h > height[i - 2] && h > height[i + 1] && h > height[i + 2]) {
					int max = Math.max(Math.max(height[i - 1], height[i - 2]), Math.max(height[i + 1], height[i + 2]));
					total += h - max;
				}
			}
			sb.append('#').append(t).append(' ').append(total).append('\n');
		}
		System.out.println(sb.toString());
	}
}