import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;

class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			String encodeStr = br.readLine();
			String decodeStr = new String(Base64.getDecoder().decode(encodeStr));

			sb.append("#").append(t).append(" ").append(decodeStr).append("\n");
		}
		System.out.println(sb.toString());
	}
}