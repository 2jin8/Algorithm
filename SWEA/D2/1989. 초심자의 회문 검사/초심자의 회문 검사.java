import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			String str = br.readLine();
			String reverse = new StringBuilder(str).reverse().toString();
			boolean isEqual = str.equals(reverse);
			sb.append("#").append(t).append(" ").append(isEqual ? 1 : 0).append("\n");
		}
		System.out.println(sb.toString());
	}
}