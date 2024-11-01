import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int N, M = 26;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[] cnt = new int[M];
		for (int i = 0; i < N; i++) {
			char first = br.readLine().charAt(0);
			cnt[first - 'a']++;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			if (cnt[i] >= 5) sb.append((char) (i + 'a'));
		}
		System.out.println(sb.toString().length() == 0 ? "PREDAJA" : sb);
	}
}