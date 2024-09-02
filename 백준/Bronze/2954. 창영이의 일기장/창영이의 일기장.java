import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] chars = br.readLine().toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			sb.append(c);
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				i += 2;
			}
		}
		System.out.println(sb);
	}
}