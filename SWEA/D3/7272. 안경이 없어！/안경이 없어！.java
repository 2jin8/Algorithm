import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			char[] chars1 = st.nextToken().toCharArray();
			char[] chars2 = st.nextToken().toCharArray();

			String ans = "DIFF";
			if (chars1.length == chars2.length) { // 길이가 다르면 다른 문자열
				int num1 = getNum(chars1);
				int num2 = getNum(chars2);

				if (num1 == num2)
					ans = "SAME";
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static int getNum(char[] chars) {
		int num = 0;
		for (char c : chars) {
			if (c == 'B') {
				num = num * 10 + 2;
			} else if (c == 'A' || c == 'D' || c == 'O' || c == 'P' || c == 'Q' || c == 'R') {
				num = num * 10 + 1;
			} else {
				num *= 10;
			}
		}
		return num;
	}
}