import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		String S = br.readLine();
		String check = "IOI";
		for (int i = 0; i < N - 1; i++) {
			check += "OI";
		}
		
		int ans = 0;
		int len = check.length();
		for (int i = 0; i + len <= M; i++) {
			if (S.substring(i, i + len).equals(check)) {
				ans++;
			}
		}
		System.out.println(ans);
	}
}