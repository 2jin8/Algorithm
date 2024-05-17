import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
	static int n, m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			String str = br.readLine();
			int len = 0;
			for (int i = 1; i <= 10; i++) {
				String s1 = str.substring(0, i);
				String s2 = str.substring(i, 2 * i);
				if (s1.equals(s2)) {
					len = i;
					break;
				}
			}
			sb.append("#").append(t).append(" ").append(len).append("\n");
		}
		System.out.println(sb.toString());
	}
}